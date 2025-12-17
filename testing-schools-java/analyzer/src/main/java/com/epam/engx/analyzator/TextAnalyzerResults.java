package com.epam.engx.analyzator;

import com.epam.engx.analyzator.sentence.SentenceAnalyzerResults;
import com.epam.engx.analyzator.word.WordAnalyzerResults;

public record TextAnalyzerResults(WordAnalyzerResults wordAnalyzerResults, SentenceAnalyzerResults sentenceAnalyzerResults) {
}
