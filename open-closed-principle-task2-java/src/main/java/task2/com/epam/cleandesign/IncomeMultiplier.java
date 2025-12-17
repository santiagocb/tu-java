package task2.com.epam.cleandesign;

final class IncomeMultiplier {

    static int multiply(int loan, int income) {
        return loan * getIncomeMultiplier(income);
    }

    private static int getIncomeMultiplier(int income) {
        return income <= 1_000 ? 1 : 2;
    }
}
