import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class Accounting {
    private IBudgetRepository repository;

    public Accounting(IBudgetRepository repository) {

        this.repository = repository;
    }

    public double totalAmount(LocalDate start, LocalDate end) {
        List<Budget> budgets = repository.getAll();
        if (!budgets.isEmpty()) {
            return ((Duration.between(start.atStartOfDay(), end.atStartOfDay()).toDays() + 1));
        }

        return 0;
    }
}
