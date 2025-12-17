package task2.com.epam.cleandesign;

public class StudentLoan implements Loan {

    @Override
    public int calculateLoan(int age, int income) {
        int loan = 100;

        if (age >= 21) {
            loan += 150;
        }

        return IncomeMultiplier.multiply(loan, income);
    }
}