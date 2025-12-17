package com.epam.engx.m1.l3.task5;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberServiceTest {

  private PrimeNumberService primeNumberService;

  @BeforeEach
  void setUp() {
    primeNumberService = new PrimeNumberService();
  }

  @Test
  void isPrime_shouldReturnFalseForNumbersLessThanOrEqualToOne() {
    // Arrange & Act & Assert
    assertFalse(primeNumberService.isPrime(-10), "-10 is not a prime number");
    assertFalse(primeNumberService.isPrime(-1), "-1 is not a prime number");
    assertFalse(primeNumberService.isPrime(0), "0 is not a prime number");
    assertFalse(primeNumberService.isPrime(1), "1 is not a prime number");
  }

  @Test
  void isPrime_shouldReturnTrueForPrimeNumbersWithinCacheRange() {
    // Arrange & Act & Assert
    assertTrue(primeNumberService.isPrime(2), "2 is a prime number");
    assertTrue(primeNumberService.isPrime(3), "3 is a prime number");
    assertTrue(primeNumberService.isPrime(13), "13 is a prime number");
    assertTrue(primeNumberService.isPrime(199), "199 is a prime number");
  }

  @Test
  void isPrime_shouldReturnFalseForNonPrimeNumbersWithinCacheRange() {
    // Arrange & Act & Assert
    assertFalse(primeNumberService.isPrime(4), "4 is not a prime number");
    assertFalse(primeNumberService.isPrime(100), "100 is not a prime number");
    assertFalse(primeNumberService.isPrime(200), "200 is not a prime number");
  }

  @Test
  void isPrime_shouldReturnFalseForPrimeNumbersOutsideCacheRange() {
    // Arrange
    int number = 250003; // A prime number just outside the cache range

    // Act
    boolean result = primeNumberService.isPrime(number);

    // Assert
    assertFalse(result, "250003 is a prime number outside cache range");
  }

  @Test
  void isPrime_shouldReturnFalseForNonPrimeNumbersOutsideCacheRange() {
    // Arrange
    int number = 250004; // A non-prime number just outside the cache range

    // Act
    boolean result = primeNumberService.isPrime(number);

    // Assert
    assertFalse(result, "250004 is not a prime number");
  }

  @Test
  void isPrime_shouldHandleVeryLargeNumbersCorrectly() {
    // Arrange
    int largePrime = 104729; // A large known prime number
    int largeNonPrime = 104730; // A large known non-prime number

    // Act & Assert
    assertTrue(primeNumberService.isPrime(largePrime), "104729 is a prime number");
    assertFalse(primeNumberService.isPrime(largeNonPrime), "104730 is not a prime number");
  }

  @Test
  void constructor_shouldInitializeCacheWithCorrectValues() {
    // Arrange & Act
    // The cache is initialized in the constructor, so the test just checks values within the range.

    // Assert (spot-checking values)
    assertTrue(primeNumberService.isPrime(17), "17 is a prime number");
    assertFalse(primeNumberService.isPrime(18), "18 is not a prime number");
    assertTrue(primeNumberService.isPrime(19), "19 is a prime number");
    assertFalse(primeNumberService.isPrime(20), "20 is not a prime number");
  }
}
