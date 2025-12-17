package com.epam.engx.m1.l3.task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

  private final StringUtils stringUtils = new StringUtils();

  @Test
  void testBothStringsEmpty() {
    assertTrue(stringUtils.startsWithIgnoreCase("", ""),
      "Empty string should start with an empty string.");
  }

  @Test
  void testMainStringEmptyPrefixNonEmpty() {
    assertFalse(stringUtils.startsWithIgnoreCase("", "Prefix"),
      "An empty main string cannot start with a non-empty prefix.");
  }

  @Test
  void testPrefixEmptyMainStringNonEmpty() {
    assertTrue(stringUtils.startsWithIgnoreCase("Hello", ""),
      "Any main string should start with an empty prefix.");
  }

  @Test
  void testMainStringAndPrefixEqualCaseInsensitive() {
    assertTrue(stringUtils.startsWithIgnoreCase("Hello", "hello"),
      "Identical strings should match regardless of case.");
    assertTrue(stringUtils.startsWithIgnoreCase("HELLO", "hello"),
      "Prefix matching should ignore case.");
  }

  @Test
  void testValidPrefixCaseInsensitive() {
    assertTrue(stringUtils.startsWithIgnoreCase("HelloWorld", "hElLo"),
      "A valid prefix should match irrespective of case.");
    assertTrue(stringUtils.startsWithIgnoreCase("JavaProgramming", "jAVa"),
      "Matching should be case-insensitive.");
  }

  @Test
  void testInvalidPrefixCaseInsensitive() {
    assertFalse(stringUtils.startsWithIgnoreCase("HelloWorld", "World"),
      "A mismatched prefix should not match.");
    assertFalse(stringUtils.startsWithIgnoreCase("JavaProgramming", "Python"),
      "A completely different prefix should not match.");
  }

  @Test
  void testPrefixLargerThanMainString() {
    assertFalse(stringUtils.startsWithIgnoreCase("Hi", "Hello"),
      "A prefix longer than the main string cannot match.");
  }

  @Test
  void testSpecialCharactersInMainStringAndPrefix() {
    assertTrue(stringUtils.startsWithIgnoreCase("!@#$$%^", "!@#"),
      "Special character prefixes should match correctly.");
    assertFalse(stringUtils.startsWithIgnoreCase("!@#$$%^", "@@#"),
      "Special character prefixes should match only if they are correct.");
  }

  @Test
  void testNumericCharacters() {
    assertTrue(stringUtils.startsWithIgnoreCase("12345", "123"),
      "Numeric prefixes should match correctly.");
    assertFalse(stringUtils.startsWithIgnoreCase("12345", "124"),
      "A mismatched numeric prefix should not match.");
  }

  @Test
  void testEmptyPrefixWithSpecialCharacters() {
    assertTrue(stringUtils.startsWithIgnoreCase("!@#$%", ""),
      "Any string, including special characters, should match an empty prefix.");
  }

  @Test
  void testOnlyWhitespace() {
    assertTrue(stringUtils.startsWithIgnoreCase("    ", " "),
      "A string starting with spaces should match a prefix with fewer spaces.");
    assertTrue(stringUtils.startsWithIgnoreCase("    ", "    "),
      "A string should match a prefix with the exact same number of spaces.");
    assertFalse(stringUtils.startsWithIgnoreCase("    ", "     "),
      "A string with fewer spaces cannot match a prefix with more spaces.");
  }

  @Test
  void testNullMainStringOrPrefix() {
    assertThrows(NullPointerException.class, () -> {
      stringUtils.startsWithIgnoreCase(null, "Prefix");
    }, "A null main string should throw a NullPointerException.");

    assertThrows(NullPointerException.class, () -> {
      stringUtils.startsWithIgnoreCase("String", null);
    }, "A null prefix should throw a NullPointerException.");

    assertThrows(NullPointerException.class, () -> {
      stringUtils.startsWithIgnoreCase(null, null);
    }, "Both main string and prefix being null should throw a NullPointerException.");
  }
}
