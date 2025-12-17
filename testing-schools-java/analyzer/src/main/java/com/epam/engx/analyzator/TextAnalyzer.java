package com.epam.engx.analyzator;

import com.epam.engx.analyzator.sentence.SentenceAnalyzer;
import com.epam.engx.analyzator.word.WordAnalyzer;

public class TextAnalyzer {
    private final WordAnalyzer wordAnalyzer;
    private final SentenceAnalyzer sentenceAnalyzer;

    public TextAnalyzer(WordAnalyzer wordAnalyzer, SentenceAnalyzer sentenceAnalyzer) {
        this.wordAnalyzer = wordAnalyzer;
        this.sentenceAnalyzer = sentenceAnalyzer;
    }

    public TextAnalyzerResults analyze(String text) {
        return new TextAnalyzerResults(wordAnalyzer.analyze(text), sentenceAnalyzer.analyze(text));
    }
}
