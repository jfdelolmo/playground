package main.duplicates;

public enum ConditionEnum {

    NAME_SURNAME_BIRTHDATE("NameSurnameBirthdate"),
    NAME_SURNAME("NameSurname"),
    NAME_BIRTHDATE("NameBirthdate"),
    SURNAME_BIRTH_DATE("SurnameBirthDate");

    private String value;

    ConditionEnum(String value){
        this.value = value;
    }
}
