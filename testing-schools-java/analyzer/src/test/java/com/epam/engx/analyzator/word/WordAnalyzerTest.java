package com.epam.engx.analyzator.word;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordAnalyzerTest {

    @Test
    void analyze_shouldReturnCorrectResultsForSimpleText() {
        // Arrange
        WordAnalyzer analyzer = new WordAnalyzer();
        String text = "This is a test sentence.";

        // Act
        WordAnalyzerResults results = analyzer.analyze(text);

        // Assert
        assertEquals(5, results.wordCount(), "Should count 5 words.");
        assertEquals(3.8, results.wordAvgLength(), 0.01, "Average word length should be 3.8.");
    }

    @Test
    void analyze_shouldHandleEmptyText() {
        // Arrange
        WordAnalyzer analyzer = new WordAnalyzer();
        String text = "";

        // Act
        WordAnalyzerResults results = analyzer.analyze(text);

        // Assert
        assertEquals(0, results.wordCount(), "Should count 0 words.");
        assertEquals(0.0, results.wordAvgLength(), "Average word length should be 0.0.");
    }

    @Test
    void analyze_shouldIgnoreNonLetterCharacters() {
        // Arrange
        WordAnalyzer analyzer = new WordAnalyzer();
        String text = "Hello, World! How are you? #100daysOfCode";

        // Act
        WordAnalyzerResults results = analyzer.analyze(text);

        // Assert
        assertEquals(6, results.wordCount(), "Should count 6 words.");
        assertEquals(4.833, results.wordAvgLength(), 0.01, "Average word length should be 4.833.");
    }
}