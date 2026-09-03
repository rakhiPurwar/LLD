package splitwise.models.expenses;

import splitwise.models.ExpenseMetadata;
import splitwise.models.User;
import splitwise.models.splits.Split;

import java.util.List;


//explain this toh yaha pe har
public abstract class Expense {
    private final User paidBy;
    private final double amount;
    private final List<Split> splits;
    private final ExpenseMetadata expenseMetadata;

    public Expense(User paidBy, double amount, List<Split> splits, ExpenseMetadata expenseMetadata) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
        this.expenseMetadata = expenseMetadata;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public ExpenseMetadata getExpenseMetadata() {
        return expenseMetadata;
    }

    public double getAmount() {
        return amount;
    }
    public List<Split> getSplits() {return splits;}


    //enforces subclasses to have their own implementations
    public abstract boolean validate();



}
