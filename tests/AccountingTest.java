import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class AccountingTest {
    Accounting accounting;

    @Mock
    IBudgetRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        accounting = new Accounting(repository);
    }

    @Test
    public void no_Budget() {
        amountShouldBe(0.0, stringToDate("20180301"), stringToDate("20180301"));

    }

    @Test
    public void period_Inside_Budget_Month() {

        List<Budget> budgets = new ArrayList<Budget>();
        budgets.add(new Budget("201801", 31));
        when(repository.getAll()).thenReturn(budgets);
        amountShouldBe(1.0, stringToDate("20180301"), stringToDate("20180301"));
    }

    private void amountShouldBe(double expected, LocalDate start, LocalDate end) {

        double totalAmount = accounting.totalAmount(start, end);
        Assert.assertEquals(expected, totalAmount, 0.0);
    }

    private LocalDate stringToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
