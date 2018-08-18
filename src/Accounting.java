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
        Period period = new Period(start, end);
        if (!budgets.isEmpty()) {
            return period.days();
        }

        return 0;
    }

    private static class Period {
        private final LocalDate start;
        private final LocalDate end;

        private Period(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
        }

        public LocalDate getStart() {
            return start;
        }

        public LocalDate getEnd() {
            return end;
        }

        private double days() {
            return ((Duration.between(getStart().atStartOfDay(), getEnd().atStartOfDay()).toDays() + 1));
        }
    }
}
