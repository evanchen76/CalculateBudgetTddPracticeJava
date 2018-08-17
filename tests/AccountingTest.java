import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class AccountingTest {
    @Test
    public void no_Budget() {
        Accounting accounting = new Accounting();
        LocalDate start = LocalDate.of(2018, 3, 1);
        LocalDate end = LocalDate.of(2018, 3, 1);

        Double totalAmount = accounting.totalAmount(start, end);
        Assert.assertEquals(0, totalAmount, 0.0);

    }
}
