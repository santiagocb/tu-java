package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    /**
     *
     * @param amount principal amount
     * @param term term of mortgage in years
     * @param interestRate rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(
            int amount, int term, double interestRate) {

        if (amount < 0 || term <= 0 || interestRate < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        // Convert interest rate into a decimal - eg. 6.5% = 0.065
        interestRate /= 100.0;

        double termInMonths = term * 12;

        if (interestRate == 0)
            return amount/termInMonths;

        double monthlyRate = interestRate / 12.0;

        // Calculate the monthly payment
        // The Math.pow() method is used calculate values raised to a power
        return (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
    }
}
