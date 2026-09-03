package splitwise.services;

import splitwise.factories.ExpenseFactories;
import splitwise.models.ExpenseMetadata;
import splitwise.models.ExpenseType;
import splitwise.models.Group;
import splitwise.models.User;
import splitwise.models.expenses.Expense;
import splitwise.models.splits.Split;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Facade over {@link UserService} and {@link GroupService} that also owns the
 * global (cross-group) balance sheet, so callers don't need to reconcile
 * per-group balances themselves.
 */
public class SplitwiseService {
    private final UserService userService;
    private final GroupService groupService;

    // globalBalanceSheet[A][B] = amount B owes A (positive) or A owes B (negative).
    // Kept symmetric: updating A->B always mirrors the inverse update on B->A.
    private final Map<String,Map<String, Double>> globalBalanceSheet;

    public SplitwiseService(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
        this.globalBalanceSheet = new ConcurrentHashMap<>();
        }

    public void addUser(User user){
        userService.addUser(user);
        // Pre-create the user's balance row so addExpense's compute() calls below
        // never hit a missing outer key.
        globalBalanceSheet.put(user.getId(), new ConcurrentHashMap<>());
    }

    public User getUser(String id){
        return userService.getUser(id);
    }

    public void createGroup(String groupId,String name, String description){
        groupService.createGroup(groupId, name, description);
    }

    public void addMemberToGroup(String userId, String groupId){
        User user = getUser(userId);
        groupService.addUserToGroup(groupId, user);
    }

    public void addExpense(String groupId, ExpenseType expenseType, double amount, String paidBy, List<Split>splits,
                           ExpenseMetadata expenseMetadata) {
        User user = getUser(paidBy);
        Expense expense = ExpenseFactories.createExpense(expenseType, amount, user, splits, expenseMetadata);

        if (!expense.validate()) {
            System.out.println("Invalid expense provided for " + expenseMetadata.getName());
        }

        // Group association is optional — an expense can be settled between users directly,
        // so only attach it to a group when one was actually specified and exists.
        if (groupId != null && groupService.containsGroup(groupId)) {
            Group group = groupService.getGroup(groupId);

            // Splits can reference any user, but only group members should be able to
            // owe/be owed within this group's expense.
            for (Split split : splits) {
                if (!group.getMembers().contains(split.getUser())) {
                    System.out.println("Error: user " + split.getUser() + " is not a member of group " + group.getName());
                }
            }

            group.addExpense(expense);
        }

        // Update global balances regardless of group membership, since debts are tracked
        // per user pair across the whole app, not just within a group.
        for (Split split : splits) {
            String paidTo = split.getUser().getId();
            double oweAmount = split.getAmount();


            // Skip the payer's own split (e.g. their share of a bill they paid for) —
            // a user can't owe themselves.
            // compute() is used instead of get+put so each side's update is a single
            // atomic operation under concurrent expense additions.
            if (!paidBy.equals(paidTo)) {
                globalBalanceSheet.get(paidBy).compute(paidTo, (k, v) -> (v == null ? 0 : v) + oweAmount);
                globalBalanceSheet.get(paidTo).compute(paidBy, (k, v) -> (v == null ? 0 : v) - oweAmount);
            }
        }

        System.out.println("Successfully added expense for " + expenseMetadata.getName());
    }

    public void showBalances(){
        boolean isEmpty = true;

        System.out.println("-----Global Balances:----");
        for (Map.Entry<String, Map<String, Double>> allBalances : globalBalanceSheet.entrySet()) {
            for(Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                // Each debt is stored on both sides (positive on the creditor's row,
                // negative on the debtor's row) so only print the positive side to
                // avoid reporting the same debt twice.
                if(userBalance.getValue() > 0){
                    isEmpty = false;
                    printBalance(allBalances.getKey(),userBalance.getKey(),userBalance.getValue());
                }
            }
        }
        if(isEmpty) System.out.println("Global Balances is empty");
        System.out.println("-----Global Balances:----");

    }

    // user1 is the creditor (owed money), user2 is the debtor — see globalBalanceSheet's sign convention.
    private void printBalance(String user1, String user2, Double amount) {
       String name1= userService.getUser(user1).getName();
       String name2 = userService.getUser(user2).getName();
        System.out.println(name2 + "owes" + name1+ ": $"+ Math.abs(amount));
    }

}
