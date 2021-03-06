package com.counter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static final String WORD_PATTERN = "\\p{L}+(-\\p{L}+)?|\\d+";
    private URL sourceUrl;
    private Map<String, Integer> wordsMap;
    private Long totalCounter;

    public WordCounter(URL sourceUrl) {
        this.sourceUrl = sourceUrl;
        this.wordsMap = new HashMap<>();
        this.totalCounter = 0L;
    }

    public void process() {
        try {
            URLConnection urlConnection = sourceUrl.openConnection();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream())
            );

            Pattern wordPattern = Pattern.compile(WORD_PATTERN);

            String line, word;
            Matcher matcher;
            Integer counter;
            while ((line = bufferedReader.readLine()) != null) {
                matcher = wordPattern.matcher(line);

                while (matcher.find()) {
                    word = matcher.group();

                    if (word.isEmpty()) {
                        continue;
                    }

                    counter = wordsMap.get(word);
                    wordsMap.put(word, (counter == null ? 0 : counter.intValue()) + 1);
                    totalCounter++;
                }
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printResult() {
        System.out.println("Result of counting words:\nTotal word count: " + totalCounter + "\n<word>: <quantity>");

        wordsMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(pair -> System.out.println(pair.getKey() + ": " + pair.getValue()));
    }

    public Long getTotalCounter() {
        return totalCounter;
    }

    public void reset() {
        wordsMap.clear();
        totalCounter = 0L;
    }
}
