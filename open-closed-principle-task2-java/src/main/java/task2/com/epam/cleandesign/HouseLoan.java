package task2.com.epam.cleandesign;

public class HouseLoan implements Loan {

    @Override
    public int calculateLoan(int age, int income) {
        int loan = 100_000;

        if (age > 30 && income > loan / 2) {
            loan *= 2;
        }

        return IncomeMultiplier.multiply(loan, income);
    }
}