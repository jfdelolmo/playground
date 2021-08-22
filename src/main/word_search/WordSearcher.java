package main.word_search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordSearcher {

    public Map<String, Long> execute(String wordToSearch, String fileName) throws IOException {
        String content = Files.readString(Path.of(fileName));
        return countHits(wordToSearch, content);
    }

    private Map<String, Long> countHits(String wordToSearch, String content) {
        String [] searchSplit = wordToSearch.split("\\W");
        String [] contentSplit = content.split("\\W");

        return Arrays.stream(contentSplit)
                .parallel()
                .filter(s -> List.of(searchSplit).contains(s))
                .collect(Collectors.groupingBy(string -> string, Collectors.counting()));
    }


}
