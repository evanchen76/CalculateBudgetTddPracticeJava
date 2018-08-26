import java.time.Duration;
import java.time.LocalDate;

class Period {
    private final LocalDate start;
    private final LocalDate end;

    Period(LocalDate start, LocalDate end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
    }

    double overlappingDays(Period otherPeriod) {
        if (hasNoOverDays(otherPeriod)) {
            return 0;
        }

        LocalDate effectiveStart = otherPeriod.start.compareTo(start) > 0 ? otherPeriod.start : start;
        LocalDate effectiveEnd = otherPeriod.end.compareTo(end) < 0 ? otherPeriod.end : end;

        return (double) (Duration.between(effectiveStart.atStartOfDay(), effectiveEnd.atStartOfDay()).toDays() + 1);
    }

    private boolean hasNoOverDays(Period otherPeriod) {
        return start.compareTo(otherPeriod.end) > 0 || end.compareTo(otherPeriod.start) < 0;
    }
}
