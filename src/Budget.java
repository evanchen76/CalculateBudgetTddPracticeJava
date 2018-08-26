import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Budget {
    public final String yearMonth;
    public final int amount;

    Budget(String yearMonth, int amount) {

        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    LocalDate lastDay() {

        return LocalDate.of(firstDay().getYear(), firstDay().getMonth(), firstDay().lengthOfMonth());

    }

    LocalDate firstDay() {
        return stringToDate(yearMonth + "01");
    }

    private LocalDate stringToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private int daysInMonth() {
        return firstDay().lengthOfMonth();
    }

    int dailyAmount() {
        return amount / daysInMonth();
    }

    double effectiveAmount(Period period) {

        return period.overlappingDays(createPeriod()) * (double) dailyAmount();
    }

    private Period createPeriod() {
        return new Period(firstDay(), lastDay());
    }

}
