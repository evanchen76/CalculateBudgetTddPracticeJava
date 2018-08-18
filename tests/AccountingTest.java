import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class AccountingTest {
    private Accounting accounting;

    @Mock
    IBudgetRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        accounting = new Accounting(repository);
    }

    @Test
    public void no_Budget() {
        amountShouldBe(0.0, "20180301", "20180301");

    }

    @Test
    public void period_Inside_Budget_Month() {

        givenBudgets(Collections.singletonList(new Budget("201801", 31)));
        amountShouldBe(1.0, "20180301", "20180301");
    }

//    @Test
//    public void period_No_Overlap_Budget_LastDay() {
//
//        givenBudgets(Collections.singletonList(new Budget("201801", 31)));
//        amountShouldBe(0.0, "20180401", "20180401");
//    }

    private void givenBudgets(List<Budget> budgets) {
        when(repository.getAll()).thenReturn(budgets);
    }

    private void amountShouldBe(double expected, String start, String end) {

        double totalAmount = accounting.totalAmount(stringToDate(start), stringToDate(end));
        Assert.assertEquals(expected, totalAmount, 0.0);
    }

    private LocalDate stringToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
