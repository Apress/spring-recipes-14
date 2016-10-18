package com.apress.springrecipes.interest;

public interface InterestCalculator {

    public void setRateCalculator(RateCalculator rateCalculator);
    public double calculate(double amount, double year);
}
