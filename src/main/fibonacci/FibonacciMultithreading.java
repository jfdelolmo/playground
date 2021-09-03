package main.fibonacci;

import java.util.HashMap;
import java.util.Map;

public class FibonacciMultithreading extends Thread {

    private final int x;
    private final Map<Integer, Integer> calculatedValues;
    public int answer;

    public FibonacciMultithreading(int x, Map<Integer, Integer> calculatedValues) {
        this.x = x;
        this.calculatedValues = calculatedValues;
    }

    public void run() {
        if (calculatedValues.containsKey(x)) {
            answer = calculatedValues.get(x);
            System.out.printf("Getting F[%d] = %d - %s %n", x, answer, Thread.currentThread().getId());
        } else {
            try {
                /*
                 * Below we are invoking 2 threads to compute separate values
                 * This will for a tree structure
                 */

                FibonacciMultithreading f1 = new FibonacciMultithreading(x - 1, calculatedValues);
                FibonacciMultithreading f2 = new FibonacciMultithreading(x - 2, calculatedValues);
                f1.start();
                f2.start();
                f1.join();
                f2.join();
                answer = f1.answer + f2.answer;
                calculatedValues.put(x, answer);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            int inputNum = 3;
            Map<Integer, Integer> calculatedValues = new HashMap<>();
            calculatedValues.put(0, 0);
            calculatedValues.put(1, 1);
            calculatedValues.put(2, 1);

            FibonacciMultithreading f = new FibonacciMultithreading(inputNum, calculatedValues);
            long start = System.currentTimeMillis();
            f.start();
            f.join();

            System.out.printf("Fibonacci[%d] = %d - %s %n", inputNum, f.answer, Thread.currentThread().getId());
            System.out.printf("Elapsed time in ms: %d%n", System.currentTimeMillis() - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
