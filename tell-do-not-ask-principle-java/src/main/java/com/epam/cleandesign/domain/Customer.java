package com.epam.cleandesign.domain;

public class Customer {

    private int id;

    private String firstName;

    private String lastName;

    private Double balance;

    private byte badCreditHistoryCount;

    public Customer(int id, String firstName, String lastName, Double balance, byte badCreditHistoryCount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.badCreditHistoryCount = badCreditHistoryCount;
    }

    public void updateBalance(Double amount) {
        balance = balance + amount;
    }

    public Double getBalance() {
        return balance;
    }

    public boolean isEligibleForMortgage(Double amountRequested) {
        boolean isEligibleForMortgage = false;

        if (badCreditHistoryCount == 0 && balance > 0) {
            isEligibleForMortgage = balance * 2 >= amountRequested;
        }
        return isEligibleForMortgage;
    }

}
