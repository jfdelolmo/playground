package main.teeing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TeeingSample {

    private static final String MIN = "MIN";
    private static final String MAX = "MAX";
    private static final String EMPLOYEE_COUNT = "EMP_COUNT";
    private static final String EMPLOYEE_LIST = "EMP_LIST";


    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "A", 100),
                new Employee(2, "B", 200),
                new Employee(3, "C", 300),
                new Employee(4, "D", 400));

        minAndMax(employees);
        employeesWithSalaryGreaterThan(employees,200);

    }

    private static void minAndMax(List<Employee> employees){
        HashMap<String, Employee> result = employees.stream().collect(
                Collectors.teeing(
                        Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
                        Collectors.minBy(Comparator.comparing(Employee::getSalary)),
                        (employee1, employee2) -> {
                            HashMap<String, Employee> map = new HashMap<>();
                            employee1.ifPresent(e -> map.put(MAX, e));
                            employee2.ifPresent(e -> map.put(MIN, e));
                            return map;
                        }
                ));

        System.out.println("\nMIN & MAX Example");
        System.out.println(MIN + ": " + result.get(MIN));
        System.out.println(MAX + ": " + result.get(MAX));
    }

    private static void employeesWithSalaryGreaterThan(List<Employee> employees, Integer salary){
        HashMap<String, Object> result = employees.stream().collect(
                Collectors.teeing(
                        Collectors.filtering(e -> e.getSalary() > salary, Collectors.counting()),
                        Collectors.filtering(e -> e.getSalary() > salary, Collectors.toList()),
                        (count, list) -> {
                            HashMap<String, Object> map = new HashMap<>();
                            map.put(EMPLOYEE_COUNT, count);
                            map.put(EMPLOYEE_LIST, list);
                            return map;
                        }
                ));

        System.out.println("\nCount & List Example");
        System.out.println(String.format("Number of employees with salary greater than %d: %s",salary, result.get(EMPLOYEE_COUNT)));
        System.out.println(result.get(EMPLOYEE_LIST));
    }

}
