import java.util.*;

// ==========================================
// Core Java Exercises 11 - 20
// ==========================================

public class CoreJava_11_20 {

    // ==========================================
    // Exercise 11: Factorial Calculator
    // ==========================================

    static void exercise11() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a non-negative integer: ");
        int n = sc.nextInt();

        long factorial = 1;

        for(int i = 1; i <= n; i++) {
            factorial *= i;
        }

        System.out.println("Factorial = " + factorial);
    }

    // ==========================================
    // Exercise 12: Method Overloading
    // ==========================================

    static int add(int a, int b) {
        return a + b;
    }

    static double add(double a, double b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }

    static void exercise12() {

        System.out.println(add(10, 20));
        System.out.println(add(5.5, 4.5));
        System.out.println(add(1, 2, 3));
    }

    // ==========================================
    // Exercise 13: Recursive Fibonacci
    // ==========================================

    static int fibonacci(int n) {

        if(n <= 1)
            return n;

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    static void exercise13() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.println(
                "Fibonacci Number = " + fibonacci(n)
        );
    }

    // ==========================================
    // Exercise 14: Array Sum and Average
    // ==========================================

    static void exercise14() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        int sum = 0;

        for(int i = 0; i < n; i++) {

            System.out.print("Enter element: ");
            arr[i] = sc.nextInt();

            sum += arr[i];
        }

        double average = (double)sum / n;

        System.out.println("Sum = " + sum);
        System.out.println("Average = " + average);
    }

    // ==========================================
    // Exercise 15: String Reversal
    // ==========================================

    static void exercise15() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        String reversed =
                new StringBuilder(str)
                        .reverse()
                        .toString();

        System.out.println("Reversed String = " + reversed);
    }

    // ==========================================
    // Exercise 16: Palindrome Checker
    // ==========================================

    static void exercise16() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String cleaned =
                input.replaceAll("[^a-zA-Z0-9]", "")
                     .toLowerCase();

        String reversed =
                new StringBuilder(cleaned)
                        .reverse()
                        .toString();

        if(cleaned.equals(reversed))
            System.out.println("Palindrome");
        else
            System.out.println("Not a Palindrome");
    }

    // ==========================================
    // Exercise 17: Class and Object Creation
    // ==========================================

    static class Car {

        String make;
        String model;
        int year;

        Car(String make,
            String model,
            int year) {

            this.make = make;
            this.model = model;
            this.year = year;
        }

        void displayDetails() {

            System.out.println(
                    make + " " +
                    model + " " +
                    year
            );
        }
    }

    static void exercise17() {

        Car c1 =
                new Car(
                        "Toyota",
                        "Camry",
                        2022
                );

        Car c2 =
                new Car(
                        "Honda",
                        "City",
                        2023
                );

        c1.displayDetails();
        c2.displayDetails();
    }

    // ==========================================
    // Exercise 18: Inheritance Example
    // ==========================================

    static class Animal {

        void makeSound() {
            System.out.println("Animal Sound");
        }
    }

    static class Dog extends Animal {

        @Override
        void makeSound() {
            System.out.println("Bark");
        }
    }

    static void exercise18() {

        Animal a =
                new Animal();

        Dog d =
                new Dog();

        a.makeSound();
        d.makeSound();
    }

    // ==========================================
    // Exercise 19: Interface Implementation
    // ==========================================

    interface Playable {

        void play();
    }

    static class Guitar
            implements Playable {

        public void play() {

            System.out.println(
                    "Playing Guitar"
            );
        }
    }

    static class Piano
            implements Playable {

        public void play() {

            System.out.println(
                    "Playing Piano"
            );
        }
    }

    static void exercise19() {

        Playable g =
                new Guitar();

        Playable p =
                new Piano();

        g.play();
        p.play();
    }

    // ==========================================
    // Exercise 20: Try Catch Example
    // ==========================================

    static void exercise20() {

        Scanner sc =
                new Scanner(System.in);

        try {

            System.out.print(
                    "Enter first number: "
            );

            int a =
                    sc.nextInt();

            System.out.print(
                    "Enter second number: "
            );

            int b =
                    sc.nextInt();

            int result =
                    a / b;

            System.out.println(
                    "Result = " +
                    result
            );

        } catch(
                ArithmeticException e
        ) {

            System.out.println(
                    "Cannot divide by zero."
            );
        }
    }

    // ==========================================
    // Main Method
    // ==========================================

    public static void main(String[] args) {

        

        // exercise11();
        // exercise12();
        // exercise13();
        // exercise14();
        // exercise15();
        // exercise16();
        // exercise17();
        // exercise18();
        // exercise19();
        exercise20();
    }
}