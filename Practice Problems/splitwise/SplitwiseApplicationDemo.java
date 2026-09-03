package splitwise;

import splitwise.models.ExpenseMetadata;
import splitwise.models.ExpenseType;
import splitwise.models.User;
import splitwise.models.splits.EqualSplit;
import splitwise.models.splits.ExactSplit;
import splitwise.models.splits.Split;
import splitwise.services.GroupService;
import splitwise.services.SplitwiseService;
import splitwise.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class SplitwiseApplicationDemo {
    public static void main(String[] args) {

        SplitwiseService service = new SplitwiseService(new UserService(),new GroupService());

        // 1. Create users
        service.addUser(new User("u1","Alice","alice@test.com"));
        service.addUser(new User("u2","Bob","bob@test.com"));
        service.addUser(new User("u3","David","david@test.com"));
        service.addUser(new User("u4","Charlie","charlie@test.com"));

        // 2. Set up a group with only u1-u3 as members.
        service.createGroup("g1","Goa trip","New year trip");
        service.addMemberToGroup("u1","g1");
        service.addMemberToGroup("u2","g1");
        service.addMemberToGroup("u3","g1");
        // u4 (Charlie) is deliberately left out of the group, to exercise
        // the non-member-in-group-expense check in Scenario B below.

        // Scenario A: valid group expense — Alice pays 900, split equally
        // among the three group members (300 each).
        List<Split> groupSplits = new ArrayList<>();
        groupSplits.add(new EqualSplit(service.getUser("u1")));
        groupSplits.add(new EqualSplit(service.getUser("u2")));
        groupSplits.add(new EqualSplit(service.getUser("u3")));
        service.addExpense("g1",ExpenseType.EQUAL,900,"u1",groupSplits,
                new ExpenseMetadata("Motel Booking",null,"Gaoa trip"));

        // Scenario B: invalid group expense — u4 is not a member of g1, so this
        // should surface the "not a member of group" validation from addExpense.
        List<Split> invalidgroups = new ArrayList<>();
        invalidgroups.add(new EqualSplit(service.getUser("u1")));
        invalidgroups.add(new EqualSplit(service.getUser("u4")));
        service.addExpense("g1", ExpenseType.EQUAL,200,"u1",invalidgroups,
                new ExpenseMetadata("Drinks",null,"invalid"));

        // Scenario C: non-group (1-on-1) expense — Bob pays 500, split exactly
        // as 100/400 between himself and David. groupId is null since this
        // expense isn't tied to any group.
        List<Split> nonGroupSplits = new ArrayList<>();
        nonGroupSplits.add(new ExactSplit(service.getUser("u2"),100));
        nonGroupSplits.add(new ExactSplit(service.getUser("u4"),400));

        service.addExpense(null,ExpenseType.EXACT,500,"u2",nonGroupSplits,
                new ExpenseMetadata("flight",null,"1 on 1"));
        service.showBalances();

    }
}
