package main.fibonacci;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class FibonacciCalculator {

    private static final Map<Integer, Integer> initialValues = Map.of(
            0, 0,
            1, 1
    );

    public static void main(String[] args) {
        int maxValue = 100;
//        long start = System.currentTimeMillis();
//        IntStream.range(0, maxValue)
//                .forEach(i -> System.out.printf("Calculate F%d: %d%n", i, calculate(i)));
//        System.out.println("First elapsed time:" + (System.currentTimeMillis()-start) );
//
//        start = System.currentTimeMillis();
//        IntStream.range(0, maxValue)
//                .forEach(i -> System.out.printf("CalculateFaster F%d: %d%n", i, calculateFaster(i)));
//        System.out.println("Second elapsed time:" + (System.currentTimeMillis()-start) );

        long start = System.currentTimeMillis();
        Map<Long, Long> calculatedValues = new HashMap<>();
        calculatedValues.put(0L,0L);
        calculatedValues.put(1L,1L);
        IntStream.range(0, maxValue)
                .forEach(i -> System.out.printf("calculateWithMemory F%d: %d%n", i, calculateWithMemory( (long)i, calculatedValues)));
        System.out.println("calculateWithMemory elapsed time:" + (System.currentTimeMillis()-start) );


    }

//    public static Long calculate(final Long value) {
//        if (value<2){
//            return value;
//        }
//        return calculate(value-1)+ calculate(value-2);
//    }
//
//    public static Long calculateFaster(final Long value) {
//        return Optional
//                .ofNullable(initialValues.get(value))
//                .orElseGet(() -> calculateFaster(value - 1) + calculate(value - 2));
//
//    }

    public static Long calculateWithMemory(final Long value, Map<Long, Long> calculated){
        return Optional.ofNullable(calculated.get(value))
                .orElseGet(()->{
                    Long newValue = calculateWithMemory(value - 1, calculated) + calculateWithMemory(value - 2, calculated);
                    calculated.put(value, newValue);
                    return newValue;
                });

    }
}
