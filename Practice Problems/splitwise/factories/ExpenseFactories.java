package splitwise.factories;

import splitwise.models.ExpenseMetadata;
import splitwise.models.ExpenseType;
import splitwise.models.User;
import splitwise.models.expenses.EqualExpense;
import splitwise.models.expenses.ExactExpense;
import splitwise.models.expenses.Expense;
import splitwise.models.expenses.PercentExpense;
import splitwise.models.splits.PercentSplit;
import splitwise.models.splits.Split;

import java.util.List;

/**
 * Builds an {@link Expense} for a given {@link ExpenseType}, computing each
 * {@link Split}'s amount according to that type's split strategy before
 * constructing the expense.
 */
public class ExpenseFactories {

    public static Expense createExpense(ExpenseType type, double amount, User paidBy, List<Split> splits,
                                         ExpenseMetadata expenseMetadata) {
        switch (type) {
            case EQUAL:
                int totalSplits = splits.size();
                // Round to the nearest cent first so every split holds a valid currency amount.
                double splitAmount = (double) (Math.round(amount * 100 / totalSplits)) / 100.0;
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }
                // Rounding each split individually can leave a few cents unaccounted for
                // (e.g. 10.00 / 3 = 3.33 x3 = 9.99); dump the leftover on the first split
                // so the splits always sum exactly to the original amount.
                splits.getFirst().setAmount(splitAmount + (amount - splitAmount * totalSplits));
                return new EqualExpense(paidBy, amount, splits, expenseMetadata);

            case EXACT:
                // Caller already supplied the exact per-person amounts on each split.
                return new ExactExpense(paidBy, amount, splits, expenseMetadata);

            case PERCENT:
                for (Split split : splits) {
                    // Splits arrive as PercentSplit for this type, carrying the percentage
                    // (not a currency amount) in getAmount(); convert it to a currency amount here.
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount * percentSplit.getAmount()) / 100.0);
                }
                return new PercentExpense(paidBy, amount, splits, expenseMetadata);

            default:
                throw new IllegalArgumentException("Invalid ExpenseType");
        }
    }
}
