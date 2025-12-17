package com.epam.engx.analyzator.sentence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SentenceAnalyzerTest {

    @Test
    void analyze_shouldReturnCorrectResultsForSimpleText() {
        // Arrange
        SentenceAnalyzer analyzer = new SentenceAnalyzer();
        String text = "This is a sentence. Here is another one.";

        // Act
        SentenceAnalyzerResults results = analyzer.analyze(text);

        // Assert
        assertEquals(2, results.sentenceCount(), "Should count 2 sentences.");
        assertEquals(18.5, results.sentenceAvgLength(), "Average length should be 18.5 characters.");
        assertEquals(0, results.complexSentenceCount(), "Should find 0 complex sentences.");
    }

    @Test
    void analyze_shouldHandleEmptyText() {
        // Arrange
        SentenceAnalyzer analyzer = new SentenceAnalyzer();
        String text = "";

        // Act
        SentenceAnalyzerResults results = analyzer.analyze(text);

        // Assert
        assertEquals(0, results.sentenceCount(), "Should count 0 sentences.");
        assertEquals(0.0, results.sentenceAvgLength(), "Average length should be 0.");
        assertEquals(0, results.complexSentenceCount(), "Should find 0 complex sentences.");
    }

    @Test
    void analyze_shouldDetectComplexSentences() {
        // Arrange
        SentenceAnalyzer analyzer = new SentenceAnalyzer();
        String text = "This is, a complex sentence; with markers. Another simple sentence.";

        // Act
        SentenceAnalyzerResults results = analyzer.analyze(text);

        // Assert
        assertEquals(2, results.sentenceCount(), "Should count 2 sentences.");
        assertEquals(32.0, results.sentenceAvgLength(), "Average length should be 32 characters.");
        assertEquals(1, results.complexSentenceCount(), "Should find 1 complex sentence.");
    }
}