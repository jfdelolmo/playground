package main.word_search;

import java.util.Map;

public class Main {

    public static void main(String... args) {
        String wordToSearch = args[0];
        String fileName = args[1];
        try {
            Map<String, Long> hits = new WordSearcher().execute(wordToSearch, fileName);

            System.out.printf("Searching '%s' in the file '%s':%n", wordToSearch, fileName);
            hits.forEach((word, count) -> {
                System.out.printf("There are %d time(s) the word '%s'%n", count, word);
            });
        } catch (Exception e) {
            System.out.printf("Error on searching in the file %s%n", fileName);
            System.out.println(e.getMessage());
        }
    }
}
