class SimpleInterestCalculator

    def setRate(rate)
        @rate = rate
    end

    def calculate(amount, year)
        amount * year * @rate
    end
end

SimpleInterestCalculator.new
