
package org.example;

import java.util.ArrayList;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {

        ArrayList<String> tasks = new ArrayList<>();

        tasks.add("Learn Java");
        tasks.add("Learn Gradle");
        tasks.add("Build CI/CD Pipeline");

        System.out.println("My Task Management Application");

        for (String task : tasks) {
            System.out.println("- " + task);
        }
    }
}