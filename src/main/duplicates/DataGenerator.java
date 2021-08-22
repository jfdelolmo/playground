package main.duplicates;

import java.util.List;

public class DataGenerator {

    public static List<Participant> allDifferent() {
        return
                List.of(
                        new Participant(1, "Sam", "Schmidt", "1976-03-14"),
                        new Participant(2, "Laura", "Merkel", "1976-03-26"),
                        new Participant(3, "Roger", "Schmidt", "2008-01-25")
                );
    }

    public static List<Participant> oneNameSurnameEquals() {
        return
                List.of(
                        new Participant(1, "Sam", "Schmidt", "1976-03-14"),
                        new Participant(2, "Laura", "Merkel", "1976-03-26"),
                        new Participant(3, "Sam", "Schmidt", "2008-01-25")
                );
    }

    public static List<Participant> oneDuplicated() {
        return
                List.of(
                        new Participant(1, "Sam", "Schmidt", "1976-03-14"),
                        new Participant(2, "Sam", "Schmidt", "1976-03-14")
                );
    }

    public static List<Participant> oneNameBirthdateDuplicatedAndOneCorrect() {
        return
                List.of(
                        new Participant(1, "Sam", "Schmidt", "1976-03-14"),
                        new Participant(2, "Sam", "Merkel", "1976-03-14"),
                        new Participant(3, "Roger", "Federer", "1981-08-08")
                );
    }
}
