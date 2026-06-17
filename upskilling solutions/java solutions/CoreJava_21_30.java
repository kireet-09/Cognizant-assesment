import java.io.*;
import java.util.*;
import java.util.stream.*;

// ==========================================
// Core Java Exercises 21 - 30
// ==========================================

public class CoreJava_21_30 {

    // ==========================================
    // Exercise 21: Custom Exception
    // ==========================================

    static class InvalidAgeException extends Exception {

        InvalidAgeException(String message) {
            super(message);
        }
    }

    static void exercise21() {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter age: ");
            int age = sc.nextInt();

            if(age < 18) {
                throw new InvalidAgeException(
                        "Age must be 18 or above."
                );
            }

            System.out.println("Eligible.");

        } catch(InvalidAgeException e) {

            System.out.println(e.getMessage());
        }
    }

    // ==========================================
    // Exercise 22: File Writing
    // ==========================================

    static void exercise22() {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter text: ");
            String text = sc.nextLine();

            FileWriter writer =
                    new FileWriter("output.txt");

            writer.write(text);

            writer.close();

            System.out.println(
                    "Data written successfully."
            );

        } catch(IOException e) {

            System.out.println(e.getMessage());
        }
    }

    // ==========================================
    // Exercise 23: File Reading
    // ==========================================

    static void exercise23() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("output.txt")
                    );

            String line;

            while((line = reader.readLine()) != null) {

                System.out.println(line);
            }

            reader.close();

        } catch(IOException e) {

            System.out.println(e.getMessage());
        }
    }

    // ==========================================
    // Exercise 24: ArrayList Example
    // ==========================================

    static void exercise24() {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> students =
                new ArrayList<>();

        System.out.print(
                "How many students? "
        );

        int n = sc.nextInt();

        sc.nextLine();

        for(int i = 0; i < n; i++) {

            System.out.print(
                    "Enter student name: "
            );

            students.add(
                    sc.nextLine()
            );
        }

        System.out.println(
                "\nStudent List:"
        );

        for(String s : students) {

            System.out.println(s);
        }
    }

    // ==========================================
    // Exercise 25: HashMap Example
    // ==========================================

    static void exercise25() {

        Scanner sc =
                new Scanner(System.in);

        HashMap<Integer,String> map =
                new HashMap<>();

        System.out.print(
                "How many entries? "
        );

        int n =
                sc.nextInt();

        sc.nextLine();

        for(int i = 0; i < n; i++) {

            System.out.print(
                    "Enter ID: "
            );

            int id =
                    sc.nextInt();

            sc.nextLine();

            System.out.print(
                    "Enter Name: "
            );

            String name =
                    sc.nextLine();

            map.put(id,name);
        }

        System.out.print(
                "Enter ID to search: "
        );

        int searchId =
                sc.nextInt();

        System.out.println(
                "Name = " +
                map.get(searchId)
        );
    }

    // ==========================================
    // Exercise 26: Thread Creation
    // ==========================================

    static class MyThread
            extends Thread {

        String message;

        MyThread(String message) {
            this.message = message;
        }

        public void run() {

            for(int i=1;i<=5;i++) {

                System.out.println(
                        message +
                        " : " + i
                );
            }
        }
    }

    static void exercise26() {

        MyThread t1 =
                new MyThread("Thread 1");

        MyThread t2 =
                new MyThread("Thread 2");

        t1.start();
        t2.start();
    }

    // ==========================================
    // Exercise 27: Lambda Expressions
    // ==========================================

    static void exercise27() {

        List<String> names =
                new ArrayList<>();

        names.add("Krishna");
        names.add("Anil");
        names.add("Ravi");
        names.add("Bhanu");

        Collections.sort(
                names,
                (a,b) -> a.compareTo(b)
        );

        System.out.println(
                "Sorted List:"
        );

        names.forEach(
                System.out::println
        );
    }

    // ==========================================
    // Exercise 28: Stream API
    // ==========================================

    static void exercise28() {

        List<Integer> numbers =
                Arrays.asList(
                        1,2,3,4,5,
                        6,7,8,9,10
                );

        List<Integer> evenNumbers =
                numbers.stream()
                       .filter(n -> n % 2 == 0)
                       .collect(
                               Collectors.toList()
                       );

        System.out.println(
                evenNumbers
        );
    }

    // ==========================================
    // Exercise 29: Records (Java 16+)
    // ==========================================

    record Person(
            String name,
            int age
    ) {}

    static void exercise29() {

        Person p1 =
                new Person(
                        "Krishna",
                        21
                );

        Person p2 =
                new Person(
                        "Ravi",
                        17
                );

        Person p3 =
                new Person(
                        "Anil",
                        25
                );

        List<Person> people =
                Arrays.asList(
                        p1,p2,p3
                );

        System.out.println(
                "All Persons:"
        );

        people.forEach(
                System.out::println
        );

        System.out.println(
                "\nAge >= 18:"
        );

        people.stream()
              .filter(
                      p -> p.age() >= 18
              )
              .forEach(
                      System.out::println
              );
    }

    // ==========================================
    // Exercise 30:
    // Pattern Matching for Switch (Java 21)
    // ==========================================

    static void identifyType(
            Object obj
    ) {

        switch(obj) {

            case Integer i ->
                    System.out.println(
                            "Integer: " + i
                    );

            case String s ->
                    System.out.println(
                            "String: " + s
                    );

            case Double d ->
                    System.out.println(
                            "Double: " + d
                    );

            case null ->
                    System.out.println(
                            "Null Value"
                    );

            default ->
                    System.out.println(
                            "Unknown Type"
                    );
        }
    }

    static void exercise30() {

        identifyType(100);

        identifyType("Hello");

        identifyType(12.5);

        identifyType(null);
    }

    // ==========================================
    // Main Method
    // ==========================================

    public static void main(String[] args) {

        // exercise21();
        // exercise22();
        // exercise23();
        // exercise24();
        // exercise25();
        // exercise26();
        // exercise27();
        // exercise28();
        // exercise29();
        exercise30();
    }
}