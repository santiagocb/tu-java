package com.epam.engx.cleandesign;

public class Worker {

    private static final double SENIOR_SALARY_FACTOR = 1.2;

    private double dailyRate;
    private double amountPerDay;
    private boolean isJunior;

    public Worker(double dailyRate, double amountPerDay) {
        this.dailyRate = dailyRate;
        this.amountPerDay = amountPerDay;
        this.isJunior = false;
    }

    public Worker(double dailyRate, double amountPerDay, boolean isJunior) {
        this.dailyRate = dailyRate;
        this.amountPerDay = amountPerDay;
        this.isJunior = isJunior;
    }

    public boolean isJunior() {
        return isJunior;
    }

    public Double calculateSalary(Double area) {
        int days = (int) Math.ceil(area / amountPerDay);
        double baseSalary = dailyRate * days;
        if (isJunior) {
            return baseSalary;
        }
        return baseSalary * SENIOR_SALARY_FACTOR;
    }

}
