package com.example.singleton;
public class Logger {
    private static Logger instance;

    private Logger() {
        // Private constructor prevents instantiation from outside
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
// Example method to demonstrate functionality
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}
