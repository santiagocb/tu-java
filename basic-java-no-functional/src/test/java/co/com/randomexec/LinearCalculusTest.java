package co.com.randomexec;

import co.com.randomexec.linearcalc.LinearCalculus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearCalculusTest {

    @ParameterizedTest
    @CsvFileSource(files = {"src/test/resources/cars.csv"}, delimiterString = ";")
    void testFindCarsDistance(double car1Speed, double car2Speed, double initialDistance, double time, double expected) {
        assertEquals(expected, round(LinearCalculus.findCarsDistance(car1Speed, car2Speed, initialDistance, time), 4));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Test
    void testIntAndDoubleDivision() {
        int integerNumber = 10;
        double floatNumber = 4.0;
        int integerThree = 3;
        assertEquals(integerNumber/integerThree, 3);
        assertEquals(integerNumber/floatNumber, 2.5);
        assertEquals(floatNumber/integerThree, 1.3333333333333333);
    }
}