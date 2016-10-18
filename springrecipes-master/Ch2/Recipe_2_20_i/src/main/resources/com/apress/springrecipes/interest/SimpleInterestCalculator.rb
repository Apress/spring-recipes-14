class SimpleInterestCalculator

    def setRateCalculator(rateCalculator)
        @rateCalculator = rateCalculator
    end

    def calculate(amount, year)
        amount * year * @rateCalculator.getAnnualRate
    end
end


SimpleInterestCalculator.new
