public class FinancialForecast {

    public static double futureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            System.out.println("    Base case reached: years = 0, returning " + currentValue);
            return currentValue;
        }

        System.out.println("    Year " + years + ": Calculating futureValue(" + currentValue + 
                         ", " + growthRate + ", " + (years - 1) + ") × " + (1 + growthRate));
        
        return futureValue(currentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static double futureValueIterative(double currentValue, double growthRate, int years) {
        double result = currentValue;
        
        for (int year = 1; year <= years; year++) {
            result = result * (1 + growthRate);
            System.out.println("    Year " + year + ": " + (result / (1 + growthRate)) + 
                             " × " + (1 + growthRate) + " = " + result);
        }
        
        return result;
    }

    public static double futureValueOptimal(double currentValue, double growthRate, int years) {
        return currentValue * Math.pow(1 + growthRate, years);
    }
}