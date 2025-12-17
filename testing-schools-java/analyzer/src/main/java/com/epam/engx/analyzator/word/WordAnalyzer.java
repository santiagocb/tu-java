package com.epam.engx.analyzator.word;

import java.util.Arrays;
import java.util.List;

public class WordAnalyzer {

    public WordAnalyzerResults analyze(String text) {
        final var words = Arrays.stream(text.split("\\P{L}+"))
                .filter(str -> !str.isEmpty())
                .toList();
        final var wordCount = words.size();
        final var wordAvgLength = calculateWordAvgLength(words);

        return new WordAnalyzerResults(wordCount, wordAvgLength);
    }

    private double calculateWordAvgLength(List<String> words) {
        if (words.isEmpty()) {
            return 0.0;
        }
        final var totalLength = words.stream()
                .map(String::length)
                .reduce(0, Integer::sum);

        return (double) totalLength / words.size();
    }
}
