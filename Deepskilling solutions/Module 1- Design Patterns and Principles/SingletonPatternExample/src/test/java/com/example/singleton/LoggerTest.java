package com.example.singleton;

public class LoggerTest {
    public static void main(String[] args) {
        Logger firstInstance = Logger.getInstance();
        Logger secondInstance = Logger.getInstance();

        System.out.println("First instance hashCode: " + firstInstance.hashCode());
        System.out.println("Second instance hashCode: " + secondInstance.hashCode());

        if (firstInstance == secondInstance) {
            System.out.println("Singleton works: both references point to the same instance.");
        } else {
            System.out.println("Singleton failed: different instances exist.");
        }

        firstInstance.log("This is a singleton logger message.");
        secondInstance.log("This is another message from the same logger instance.");
    }
}
