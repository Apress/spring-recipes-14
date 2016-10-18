package com.apress.springrecipes.interest;

public class FixedRateCalculator implements RateCalculator {

    private double rate;

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAnnualRate() {
        return rate;
    }

    public double getMonthlyRate() {
        return rate / 12;
    }

    public double getDailyRate() {
        return rate / 365;
    }
}
