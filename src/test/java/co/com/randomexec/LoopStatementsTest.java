package co.com.randomexec;

import co.com.randomexec.loopstatements.LoopStatements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoopStatementsTest {

    @Test
    public void testSumOfFibonacciNumbersSimpleCase() {

        assertEquals(33, LoopStatements.sumOfFibonacciNumbers(8));
        assertEquals(143, LoopStatements.sumOfFibonacciNumbers(11));


    }

    @Test
    public void testSumOddDigitsSimpleCase() {

        assertEquals(4, LoopStatements.sumOddDigits(1234));
        assertEquals(0, LoopStatements.sumOddDigits(246));
        assertEquals(3, LoopStatements.sumOddDigits(111));
        assertEquals(1, LoopStatements.sumOddDigits(1));
    }
}
