package Threads;

import SharedResources.SharedInput;
import SharedResources.SharedResult;
import java.util.concurrent.atomic.AtomicBoolean;
import static java.lang.Math.sqrt;

public class ThreadPrimeChecker implements Runnable {
        SharedInput tasks;
        SharedResult results;
        private static volatile AtomicBoolean isFinished = new AtomicBoolean(false);

        public ThreadPrimeChecker( SharedInput tasks, SharedResult results) {
            this.tasks = tasks;
            this.results = results;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Integer prime = tasks.getTask();
                    if (prime != null) {
                        boolean isPrime = checkPrime(prime);
                        if (isPrime) {
                            results.addOutput(prime.toString() + " is prime! Thread: " + this.toString());
                        } else {
                            results.addOutput(prime.toString() + " is not prime! Thread: " + this.toString());
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                    break;
                }
                if (isFinished.get() && tasks.getSize() == 0) {
                    break;
                }
            }
        }

        private boolean checkPrime(Integer prime) throws InterruptedException {
            System.out.println("Calculating");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {
                throw new InterruptedException("przerwano checkPrime");
            }

            if (prime <= 1) {
                return false;
            }

            for (int i = 2; i<= (int)sqrt(prime);i++) {
                if (prime % i == 0) {
                    return false;
                }
            }
            return true;
        }

        public static void finish() {
            isFinished.set(true);
        }
}
