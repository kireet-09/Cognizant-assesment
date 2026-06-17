public class Ex40 {

    public static void main(String[] args)
            throws Exception {

        Runnable task = () -> {

            System.out.println(
                    "Running in: "
                            + Thread.currentThread()
            );
        };

        Thread vt1 =
                Thread.startVirtualThread(task);

        Thread vt2 =
                Thread.startVirtualThread(task);

        Thread vt3 =
                Thread.startVirtualThread(task);

        vt1.join();
        vt2.join();
        vt3.join();

        System.out.println(
                "All Virtual Threads Completed"
        );
    }
}