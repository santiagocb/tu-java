package com.epam.engx.m1.l3.task5;

class PrimeNumberService {

  private final boolean[] primeNumbersCache = new boolean[250000];

  PrimeNumberService() {
    for (int i = 0; i < primeNumbersCache.length; ++i) {
      primeNumbersCache[i] = verifyPrime(i);
    }
  }

  boolean isPrime(int number) {
    // Handle negative numbers and invalid cases
    if (number <= 1) {
      return false; // By definition, negative numbers, 0, and 1 are not prime
    }

    if (number < primeNumbersCache.length) {
      return primeNumbersCache[number];
    }

    return verifyPrime(number);
  }

  private static boolean verifyPrime(int number) {
    if(number <= 1){
      return false;
    }
    for (int i = 2; i <= number / 2; ++i) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }

}
