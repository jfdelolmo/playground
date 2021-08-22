package main.duplicates;

import java.util.function.Function;

public class ParticipantDuplicateConditions {

    ConditionEnum condition;
    Function<Participant, String> keyValueFunction;

    public ParticipantDuplicateConditions(ConditionEnum condition, Function<Participant, String> keyValueFunction) {
        this.condition = condition;
        this.keyValueFunction = keyValueFunction;
    }

    public ConditionEnum getCondition(){
        return condition;
    }

    public String getKeyName() {
        return condition.name();
    }

    public String applyFunction(Participant p){
        return keyValueFunction.apply(p);
    }

}
