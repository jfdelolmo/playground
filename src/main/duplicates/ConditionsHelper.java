package main.duplicates;

import java.util.List;

import static main.duplicates.ConditionEnum.*;

public class ConditionsHelper {

    private static final List<ParticipantDuplicateConditions> conditions = List.of(
            new ParticipantDuplicateConditions(NAME_SURNAME_BIRTHDATE, p -> p.getName() + p.getSurname() + p.getBirthdate()),
            new ParticipantDuplicateConditions(NAME_SURNAME, p -> p.getName() + p.getSurname()),
            new ParticipantDuplicateConditions(NAME_BIRTHDATE, p -> p.getName() + p.getBirthdate()),
            new ParticipantDuplicateConditions(SURNAME_BIRTH_DATE, p -> p.getSurname() + p.getBirthdate())
    );

    public static List<ParticipantDuplicateConditions> getConditions(){
        return conditions;
    }


}
