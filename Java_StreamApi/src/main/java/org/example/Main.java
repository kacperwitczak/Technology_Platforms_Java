package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String source = args[0];
        String destination = args[1];
        ImageProcessor imageProcessor = new ImageProcessor(source, destination);

        int n = 5;
        long time = System.currentTimeMillis();
        imageProcessor.process(n);
        System.out.println("Time for " + n + " threads: " + (System.currentTimeMillis() - time));

        n = 10;
        time = System.currentTimeMillis();
        imageProcessor.process(n);
        System.out.println("Time for " + n + " threads: " + (System.currentTimeMillis() - time));

        n = 20;
        time = System.currentTimeMillis();
        imageProcessor.process(n);
        System.out.println("Time for " + n + " threads: " + (System.currentTimeMillis() - time));

    }
}