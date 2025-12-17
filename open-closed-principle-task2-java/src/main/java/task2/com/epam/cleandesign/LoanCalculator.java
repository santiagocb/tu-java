package task2.com.epam.cleandesign;

import java.util.HashMap;
import java.util.Map;

final class LoanCalculator {

    private final Map<String, Loan> loanTypes = new HashMap<>();

    public void registerLoanType(String name, Loan loan) {
        loanTypes.put(name, loan);
    }

    public int calculateLoan(String loanTypeName, int age, int income) {

        if (loanTypes.containsKey(loanTypeName)) {
            Loan loanType = loanTypes.get(loanTypeName);
            return loanType.calculateLoan(age, income);
        } else {
            throw new IllegalArgumentException("Unknown loan type: " + loanTypeName);
        }
    }
}



//Movie archive is damaged or empty
//Total: 0 actors, 0 cameramen, superstars: []
//Budget: 61000000 initial, 42880500 spent, 18119500 economy
//Recruiter: 'Andrew Carnegie', earned money: 234000, salary: 2000
//Accountant: 'William Welch Deloitte', earned money: 292500, salary: 2500
//Actor: 'Mark Hamill', earned money: 5850000, salary: 50000
//Actor: 'Harrison Ford', earned money: 5850000, salary: 50000
//Actor: 'Carrie Fischer', earned money: 5850000, salary: 50000
//Actor: 'Billy Dee Williams', earned money: 5850000, salary: 50000
//Actor: 'Anthony Daniels', earned money: 5850000, salary: 50000
//Actor: 'David Prowse', earned money: 5850000, salary: 50000
//Actor: 'Peter Mayhew', earned money: 5850000, salary: 50000
//Cameraman: 'John Campbell', earned money: 702000, salary: 6000
//Cameraman: 'Bill Neil', earned money: 702000, salary: 6000