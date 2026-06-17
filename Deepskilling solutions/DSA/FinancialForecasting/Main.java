public class Main {

    public static void main(String[] args) {

        double currentValue = 10000;
        double growthRate = 0.10;
        int years = 5;

        double recursiveResult = FinancialForecast.futureValue(currentValue, growthRate, years);
        double iterativeResult = FinancialForecast.futureValueIterative(currentValue, growthRate, years);
        double optimalResult = FinancialForecast.futureValueOptimal(currentValue, growthRate, years);

        System.out.printf("%nFuture Value after %d years%n", years);
        System.out.printf("Recursive : %.2f%n", recursiveResult);
        System.out.printf("Iterative : %.2f%n", iterativeResult);
        System.out.printf("Optimal   : %.2f%n", optimalResult);

        double gain = recursiveResult - currentValue;
        System.out.printf("%nTotal Gain: %.2f%n", gain);

        System.out.println("\nComplexity Analysis");
        System.out.println("Recursive -> Time: O(n), Space: O(n)");
        System.out.println("Iterative -> Time: O(n), Space: O(1)");
        System.out.println("Optimal   -> Time: O(1), Space: O(1)");
    }
}
