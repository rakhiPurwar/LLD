package splitwise.models.expenses;

import splitwise.models.ExpenseMetadata;
import splitwise.models.User;
import splitwise.models.splits.EqualSplit;
import splitwise.models.splits.PercentSplit;
import splitwise.models.splits.Split;

import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(User paidBy, double amount, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(paidBy, amount, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        double totalSplitAmount = 0;
        for (Split split : getSplits()) {
            if(!(split instanceof EqualSplit)) return false;
            totalSplitAmount+= split.getAmount();
        }

        //thoda error ka case chorna chahiye in case of double
        return Math.abs(getAmount() - totalSplitAmount) < 0.0001;
    }

}
