package splitwise.models.expenses;

import splitwise.models.ExpenseMetadata;
import splitwise.models.User;
import splitwise.models.splits.PercentSplit;
import splitwise.models.splits.Split;

import java.util.List;

public class PercentExpense extends Expense{

    public PercentExpense(User paidBy, double amount, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(paidBy, amount, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        double totalPercent = 0;
        for (Split split : getSplits()) {
            if(!(split instanceof PercentSplit)) return false;
            totalPercent += ((PercentSplit)split).getPercent();
        }

        //thoda error ka case chorna chahiye in case of double dont directly equate them, we define some error
        return Math.abs(100.0 - totalPercent) < 0.0001;
    }

}
