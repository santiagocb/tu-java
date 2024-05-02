package co.com.randomexec.loopstatements;


public class LoopStatements {

    public static int sumOfFibonacciNumbers(int n) {
        if (n <= 1)
            return 0;
        return sumOfFibonacciNumbers(n-1) + sumOfFibonacciNumbers(n-2) + 1;
    }

    public static int sumOddDigits(int n) {

        int sum = 0;
        int digit = 0;
        while (n != 0) {
            digit = n % 10;
            if (digit % 2 != 0) sum += digit;
            n /= 10;
        }
        return sum;
    }
}
