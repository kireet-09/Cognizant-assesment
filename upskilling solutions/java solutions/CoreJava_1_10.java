import java.util.*;

// ==========================================
// Core Java Exercises 1 - 10
// ==========================================

public class CoreJava_1_10 {

    // ==========================================
    // Exercise 1: Hello World Program
    // ==========================================

    static void exercise1() {
        System.out.println("Hello, World!");
    }

    // ==========================================
    // Exercise 2: Simple Calculator
    // ==========================================

    static void exercise2() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double a = sc.nextDouble();

        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        System.out.println("Choose Operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");

        int choice = sc.nextInt();

        switch(choice) {

            case 1:
                System.out.println("Result = " + (a + b));
                break;

            case 2:
                System.out.println("Result = " + (a - b));
                break;

            case 3:
                System.out.println("Result = " + (a * b));
                break;

            case 4:
                if(b != 0)
                    System.out.println("Result = " + (a / b));
                else
                    System.out.println("Cannot divide by zero");
                break;

            default:
                System.out.println("Invalid Choice");
        }
    }

    // ==========================================
    // Exercise 3: Even or Odd Checker
    // ==========================================

    static void exercise3() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int num = sc.nextInt();

        if(num % 2 == 0)
            System.out.println(num + " is Even");
        else
            System.out.println(num + " is Odd");
    }

    // ==========================================
    // Exercise 4: Leap Year Checker
    // ==========================================

    static void exercise4() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter year: ");
        int year = sc.nextInt();

        if((year % 4 == 0 && year % 100 != 0)
                || (year % 400 == 0)) {

            System.out.println(year + " is a Leap Year");

        } else {

            System.out.println(year + " is NOT a Leap Year");
        }
    }

    // ==========================================
    // Exercise 5: Multiplication Table
    // ==========================================

    static void exercise5() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        int num = sc.nextInt();

        System.out.println("Multiplication Table of " + num);

        for(int i = 1; i <= 10; i++) {

            System.out.println(
                    num + " x " + i + " = " + (num * i)
            );
        }
    }

    // ==========================================
    // Exercise 6: Data Type Demonstration
    // ==========================================

    static void exercise6() {

        int age = 21;
        float height = 5.9f;
        double salary = 50000.75;
        char grade = 'A';
        boolean passed = true;

        System.out.println("Integer Value: " + age);
        System.out.println("Float Value: " + height);
        System.out.println("Double Value: " + salary);
        System.out.println("Char Value: " + grade);
        System.out.println("Boolean Value: " + passed);
    }

    // ==========================================
    // Exercise 7: Type Casting Example
    // ==========================================

    static void exercise7() {

        double d = 15.89;

        int i = (int)d;

        System.out.println("Original Double = " + d);
        System.out.println("After Casting to Int = " + i);

        int num = 25;

        double d2 = (double)num;

        System.out.println("Original Int = " + num);
        System.out.println("After Casting to Double = " + d2);
    }

    // ==========================================
    // Exercise 8: Operator Precedence
    // ==========================================

    static void exercise8() {

        int result1 = 10 + 5 * 2;

        int result2 = (10 + 5) * 2;

        int result3 = 20 / 2 + 3 * 4;

        System.out.println("10 + 5 * 2 = " + result1);

        System.out.println("(10 + 5) * 2 = " + result2);

        System.out.println("20 / 2 + 3 * 4 = " + result3);

        System.out.println(
                "Multiplication and Division have higher precedence than Addition."
        );
    }

    // ==========================================
    // Exercise 9: Grade Calculator
    // ==========================================

    static void exercise9() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter marks (0-100): ");
        int marks = sc.nextInt();

        if(marks >= 90 && marks <= 100)
            System.out.println("Grade A");

        else if(marks >= 80)
            System.out.println("Grade B");

        else if(marks >= 70)
            System.out.println("Grade C");

        else if(marks >= 60)
            System.out.println("Grade D");

        else
            System.out.println("Grade F");
    }

    // ==========================================
    // Exercise 10: Number Guessing Game
    // ==========================================

    static void exercise10() {

        Scanner sc = new Scanner(System.in);

        Random random = new Random();

        int target = random.nextInt(100) + 1;

        int guess;

        System.out.println("Guess a number between 1 and 100");

        do {

            System.out.print("Enter your guess: ");

            guess = sc.nextInt();

            if(guess > target) {

                System.out.println("Too High!");

            } else if(guess < target) {

                System.out.println("Too Low!");

            } else {

                System.out.println("Congratulations!");
                System.out.println("You guessed correctly.");
            }

        } while(guess != target);
    }

    // ==========================================
    // Main Method
    // ==========================================

    public static void main(String[] args) {

        

        // exercise1();
        // exercise2();
        // exercise3();
        // exercise4();
        // exercise5();
        // exercise6();
        // exercise7();
        // exercise8();
        // exercise9();
        exercise10();
    }
}