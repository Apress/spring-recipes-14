import com.apress.springrecipes.interest.InterestCalculator;

class SimpleInterestCalculator implements InterestCalculator {

    double rate

    double calculate(double amount, double year) {
        return amount * year * rate
    }
}
