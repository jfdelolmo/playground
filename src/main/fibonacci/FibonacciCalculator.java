package main.fibonacci;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class FibonacciCalculator {

    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final BigDecimal ONE = BigDecimal.ONE;
    private static final BigDecimal TWO = BigDecimal.valueOf(2);

    public static void main(String[] args) {
        int lastValue = 100;

        Map<BigDecimal, BigDecimal> previousValuesMap = new HashMap<>();
        previousValuesMap.put(ZERO, ZERO);
        previousValuesMap.put(ONE, ONE);
        previousValuesMap.put(TWO, ONE);

        long start = System.currentTimeMillis();

        IntStream.range(0, lastValue)
                .mapToObj(BigDecimal::valueOf)
                .map(value -> FibonacciCalculator.buildData(value, previousValuesMap))
                .forEach(FibonacciCalculator::printValue);

        System.out.println("Elapsed time:" + (System.currentTimeMillis() - start));
    }

    private static AbstractMap.SimpleEntry<BigDecimal, BigDecimal> buildData(BigDecimal value, Map<BigDecimal, BigDecimal> previousValuesMap) {
        return new AbstractMap.SimpleEntry<>(value, fibonacciOfValue(value, previousValuesMap));
    }

    private static void printValue(AbstractMap.SimpleEntry<BigDecimal, BigDecimal> entry) {
        System.out.printf("Fibonacci[%s] = %s%n", entry.getKey().toPlainString(), entry.getValue().toPlainString());
    }

    public static BigDecimal fibonacciOfValue(final BigDecimal value, Map<BigDecimal, BigDecimal> previousValuesMap) {
        return Optional.ofNullable(previousValuesMap.get(value))
                .orElseGet(() -> {
                    BigDecimal newValue = fibonacciOfValue(value.subtract(ONE), previousValuesMap)
                            .add(fibonacciOfValue(value.subtract(TWO), previousValuesMap));
                    previousValuesMap.put(value, newValue);
                    return newValue;
                });
    }

}
