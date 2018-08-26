import java.time.LocalDate;
import java.util.List;

class Accounting {
    private IBudgetRepository repository;

    Accounting(IBudgetRepository repository) {

        this.repository = repository;
    }

    double totalAmount(LocalDate start, LocalDate end) {
        List<Budget> budgets = repository.getAll();
        Period period = new Period(start, end);
        double totalAmount = 0;
        for (Budget budget : budgets) {

            totalAmount += budget.effectiveAmount(period);

        }
        return totalAmount;

    }

}
