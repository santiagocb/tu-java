package task2.com.epam.cleandesign;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoanCalculatorTest {

    private final LoanCalculator calculator = new LoanCalculator();

    public LoanCalculatorTest() {
        calculator.registerLoanType("student", new StudentLoan());
        calculator.registerLoanType("car", new CarLoan());
        calculator.registerLoanType("house", new HouseLoan());
    }

    @Test
    public void studentLoanForYoungPoorPerson() {

        assertThat(calculator.calculateLoan("student", 18, 100), is(100));
    }

    @Test
    public void studentLoanForOldPerson() {
        assertThat(calculator.calculateLoan("student", 21, 100), is(250));
    }

    @Test
    public void studentLoanForRichPerson() {
        assertThat(calculator.calculateLoan("student", 18, 2_000), is(200));
    }

    @Test
    public void carLoanForYoungPoorPerson() {
        assertThat(calculator.calculateLoan("car", 20, 500), is(2_000));
    }

    @Test
    public void carLoanForAdultPoorPerson() {
        assertThat(calculator.calculateLoan("car", 45, 500), is(3_000));
    }

    @Test
    public void carLoanForOldPoorPerson() {
        assertThat(calculator.calculateLoan("car", 60, 500), is(3_500));
    }

    @Test
    public void carLoanForYoungRichPerson() {
        assertThat(calculator.calculateLoan("car", 20, 2_000), is(4_000));
    }

    @Test
    public void carLoanForAdultRichPerson() {
        assertThat(calculator.calculateLoan("car", 45, 2_000), is(6_000));
    }

    @Test
    public void carLoanForOldRichPerson() {
        assertThat(calculator.calculateLoan("car", 60, 2_000), is(7_000));
    }

    @Test
    public void houseLoanForYoungPoorPerson() {
        assertThat(calculator.calculateLoan("house",20, 500), is(100_000));
    }

    @Test
    public void houseLoanForOldPoorPerson() {
        assertThat(calculator.calculateLoan("house",60, 500), is(100_000));
    }

    @Test
    public void houseLoanForYoungRichPerson() {
        assertThat(calculator.calculateLoan("house",20, 65_000), is(200_000));
    }

    @Test
    public void houseLoanForOldRichPerson() {
        assertThat(calculator.calculateLoan("house",60, 65_000), is(400_000));
    }
}
