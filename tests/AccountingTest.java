import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AccountingTest {
    Accounting accounting = new Accounting();

    @Test
    public void no_Budget() {
        amountShouldBe(0.0, stringToDate("20180301"), stringToDate("20180301"));

    }

    private void amountShouldBe(double expected, LocalDate start, LocalDate end) {

        Double totalAmount = accounting.totalAmount(start, end);
        Assert.assertEquals(expected, totalAmount, 0.0);
    }

    private LocalDate stringToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
