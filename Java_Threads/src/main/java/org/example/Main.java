package org.example;

import SharedResources.SharedInput;
import SharedResources.SharedResult;
import Threads.ThreadPrimeChecker;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int threadAmount = 1;
        if (args.length > 0) {
            try {
                threadAmount = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Wrong format!");
            }
        }

        SharedInput sharedInput = new SharedInput();
        SharedResult sharedResult = new SharedResult();

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i<threadAmount;i++) {
            ThreadPrimeChecker r = new ThreadPrimeChecker(sharedInput, sharedResult);
            Thread t = new Thread(r);
            t.start();
            threads.add(t);
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int numberCounter = 0;

        while (!input.equals("q")) {
            try {
                Integer prime = Integer.parseInt(input);
                sharedInput.addTask(prime);
                numberCounter++;
            } catch (NumberFormatException e) {
                System.out.println("Wrong format!");
            }
            input = sc.nextLine();
        }

        System.out.println("Finishing");

        sharedInput.close();

        ThreadPrimeChecker.finish();

        for (var t : threads) {
            try {
                t.join();
            } catch (InterruptedException ignored) {
                System.out.println("Nie udalo sie zakonczyc!");
            }
        }

        System.out.println("Number of inputs: " + numberCounter);
        sharedResult.printResult();
    }
}