package main.duplicates;

import java.util.*;
import java.util.stream.Collectors;

import static main.duplicates.ConditionEnum.*;

public class DuplicateChecker {

    public static void main(String... args) {

        doCheck("oneDuplicated", DataGenerator.oneDuplicated());
        doCheck("allDifferent", DataGenerator.allDifferent());
        doCheck("oneWarning_name_surname", DataGenerator.oneNameSurnameEquals());
        doCheck("oneNameBirthdateDuplicatedAndOneCorrect", DataGenerator.oneNameBirthdateDuplicatedAndOneCorrect());

    }

    private static void doCheck(String useCase, List<Participant> participants) {

        Map<ConditionEnum, Map<String, List<Participant>>> map = new HashMap<>();
        ConditionsHelper.getConditions().forEach(k ->
                participants.forEach(p -> {
                            String key = k.applyFunction(p);
                            map.putIfAbsent(k.getCondition(), new HashMap<>());
                            map.get(k.getCondition()).putIfAbsent(key, new ArrayList<>());
                            map.get(k.getCondition()).get(key).add(p);
                        }
                )
        );

        System.out.println("\n" + useCase);
        System.out.println("_______________________________");

        List<Participant> duplicated = getListFromMap(NAME_SURNAME_BIRTHDATE, map);
        List<Participant> warning_name_surname = getListFromMap(NAME_SURNAME, map);
        List<Participant> warning_name_birthdate = getListFromMap(NAME_BIRTHDATE, map);
        List<Participant> warning_surname_birthdate = getListFromMap(SURNAME_BIRTH_DATE, map);

        if (duplicated.size() > 0) {
            System.out.println("There are duplicated values:");
            duplicated.forEach(System.out::println);
        } else if (warning_name_surname.size() > 0) {
            printWarning(warning_name_surname);
        } else if (warning_name_birthdate.size() > 0) {
            printWarning(warning_name_birthdate);
        } else if (warning_surname_birthdate.size() > 0) {
            printWarning(warning_surname_birthdate);
        }

        Set<Integer> invalidParticipants = duplicated.stream().map(Participant::getId).collect(Collectors.toSet());
        invalidParticipants.addAll(warning_name_surname.stream().map(Participant::getId).collect(Collectors.toSet()));
        invalidParticipants.addAll(warning_name_birthdate.stream().map(Participant::getId).collect(Collectors.toSet()));
        invalidParticipants.addAll(warning_surname_birthdate.stream().map(Participant::getId).collect(Collectors.toSet()));

        List<Participant> uniqueParticipants = new ArrayList<>();
        if (invalidParticipants.size() > 0) {
            uniqueParticipants = participants.stream().filter(f -> !invalidParticipants.contains(f.getId())).collect(Collectors.toList());
        }
        if (uniqueParticipants.size() > 0) {
            System.out.println("Unique participants: ");
            uniqueParticipants.forEach(System.out::println);
        }


    }

    private static void printWarning(List<Participant> list) {
        System.out.println("There are possible duplicates");
        list.forEach(System.out::println);
    }

    private static List<Participant> getListFromMap(ConditionEnum keyToExtract, Map<ConditionEnum, Map<String, List<Participant>>> map) {
        return map.get(keyToExtract).entrySet().stream().filter(f -> f.getValue().size() > 1)
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toList());
    }
}
