import com.apress.springrecipes.interest.InterestCalculator;
import com.apress.springrecipes.interest.RateCalculator;

class SimpleInterestCalculator implements InterestCalculator {

    RateCalculator rateCalculator

    double calculate(double amount, double year) {
        return amount * year * rateCalculator.getAnnualRate()
    }
}
