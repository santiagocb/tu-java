package com.epam.engx.analyzator.sentence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SentenceAnalyzer {

    private static final String[] COMPLEX_SENTENCE_MARKERS = new String[] { ",", ":", ";"};

    public SentenceAnalyzerResults analyze(String text) {
        final var sentences = Arrays.stream(text.split("\\."))
                .map(String::trim)
                .filter(string -> !string.isEmpty())
                .toList();
        final var sentenceAvgLength = calculateSentenceAvgLength(sentences);
        final var complexSentencesCount = calculateComplexSentencesCount(sentences);

        return new SentenceAnalyzerResults(sentences.size(), sentenceAvgLength, complexSentencesCount);
    }

    private double calculateSentenceAvgLength(List<String> sentences) {
        if (sentences.isEmpty()) {
            return 0.0;
        }
        final var totalLength = sentences.stream()
                .map(String::length)
                .reduce(0, Integer::sum);
        return (double) totalLength / sentences.size();
    }

    private int calculateComplexSentencesCount(List<String> sentences) {
        return Math.toIntExact(sentences.stream()
                .filter(this::isComplexSentence)
                .count());
    }

    private boolean isComplexSentence(String sentence) {
        return Stream.of(COMPLEX_SENTENCE_MARKERS).anyMatch(sentence::contains);
    }
}
