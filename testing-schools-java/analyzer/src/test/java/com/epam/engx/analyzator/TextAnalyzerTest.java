package com.epam.engx.analyzator;


import com.epam.engx.analyzator.sentence.SentenceAnalyzer;
import com.epam.engx.analyzator.word.WordAnalyzer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextAnalyzerTest {

    @Test
    void analyze_shouldCombineResultsFromSentenceAndWordAnalyzers() {
        // Arrange
        WordAnalyzer wordAnalyzer = new WordAnalyzer();
        SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        TextAnalyzer textAnalyzer = new TextAnalyzer(wordAnalyzer, sentenceAnalyzer);
        String text = "This is a simple sentence. Another sentence here, is complex!";

        // Act
        TextAnalyzerResults results = textAnalyzer.analyze(text);

        // Assert
        // Word-related assertions
        assertEquals(10, results.wordAnalyzerResults().wordCount(), "Should count 10 words.");
        assertEquals(4.9, results.wordAnalyzerResults().wordAvgLength(), 0.01, "Average word length should be 4.9.");

        // Sentence-related assertions
        assertEquals(2, results.sentenceAnalyzerResults().sentenceCount(), "Should count 2 sentences.");
        assertEquals(29.5, results.sentenceAnalyzerResults().sentenceAvgLength(), "Average sentence length should be 29.5.");
        assertEquals(1, results.sentenceAnalyzerResults().complexSentenceCount(), "Should find 1 complex sentence.");
    }

    @Test
    void analyze_shouldHandleEmptyText() {
        // Arrange
        WordAnalyzer wordAnalyzer = new WordAnalyzer();
        SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        TextAnalyzer textAnalyzer = new TextAnalyzer(wordAnalyzer, sentenceAnalyzer);
        String text = "";

        // Act
        TextAnalyzerResults results = textAnalyzer.analyze(text);

        // Assert
        // Word-related assertions
        assertEquals(0, results.wordAnalyzerResults().wordCount(), "Should count 0 words.");
        assertEquals(0.0, results.wordAnalyzerResults().wordAvgLength(), "Average word length should be 0.0.");

        // Sentence-related assertions
        assertEquals(0, results.sentenceAnalyzerResults().sentenceCount(), "Should count 0 sentences.");
        assertEquals(0.0, results.sentenceAnalyzerResults().sentenceAvgLength(), "Average sentence length should be 0.0.");
        assertEquals(0, results.sentenceAnalyzerResults().complexSentenceCount(), "Should find 0 complex sentences.");
    }
}