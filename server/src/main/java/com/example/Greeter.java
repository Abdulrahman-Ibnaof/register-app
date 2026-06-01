package com.example;

/**
 * Provides greeting messages.
 */
public class Greeter {

    /**
     * Returns a greeting message for the provided name.
     *
     * @param someone Name of the person to greet.
     * @return Greeting message.
     */
    public String greet(String someone) {
        return String.format("Hello, %s!", someone);
    }
}