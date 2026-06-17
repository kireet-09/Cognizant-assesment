import java.util.concurrent.*;

public class Ex41 {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        Callable<Integer> task = () -> {

            int sum = 0;

            for(int i = 1; i <= 10; i++) {

                sum += i;
            }

            return sum;
        };

        try {

            Future<Integer> future =
                    executor.submit(task);

            System.out.println(
                    "Task Submitted..."
            );

            Integer result =
                    future.get();

            System.out.println(
                    "Result = " + result
            );

        } catch(Exception e) {

            System.out.println(e);
        }

        executor.shutdown();
    }
}