package splitwise.models.expenses;

import splitwise.models.ExpenseMetadata;
import splitwise.models.User;
import splitwise.models.splits.ExactSplit;
import splitwise.models.splits.Split;

import java.util.List;

public class ExactExpense extends Expense{

    public ExactExpense(User paidBy, double amount, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(paidBy, amount, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        double totalExact = 0;
        for (Split split : getSplits()) {
            if(!(split instanceof ExactSplit)) return false;
            totalExact += split.getAmount();
        }

        //thoda error ka case chorna chahiye in case of double
        return Math.abs(getAmount()  - totalExact) < 0.0001;
    }

}
