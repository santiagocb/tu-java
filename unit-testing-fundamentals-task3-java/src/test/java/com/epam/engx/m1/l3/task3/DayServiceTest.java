package com.epam.engx.m1.l3.task3;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DayServiceTest {

  private final DayService dayService = new DayService();

  @Test
  void testSaturdayIsNotWorkingDay() {
    LocalDate saturday = LocalDate.of(2023, 10, 7); // A Saturday
    assertFalse(dayService.isTodayWorkingDay(saturday), "Saturday should not be a working day.");
  }

  @Test
  void testSundayIsNotWorkingDay() {
    LocalDate sunday = LocalDate.of(2023, 10, 8); // A Sunday
    assertFalse(dayService.isTodayWorkingDay(sunday), "Sunday should not be a working day.");
  }

  @Test
  void testMondayIsWorkingDay() {
    LocalDate monday = LocalDate.of(2023, 10, 9); // A Monday
    assertTrue(dayService.isTodayWorkingDay(monday), "Monday should be a working day.");
  }

  @Test
  void testTuesdayIsWorkingDay() {
    LocalDate tuesday = LocalDate.of(2023, 10, 10); // A Tuesday
    assertTrue(dayService.isTodayWorkingDay(tuesday), "Tuesday should be a working day.");
  }

  @Test
  void testWednesdayIsWorkingDay() {
    LocalDate wednesday = LocalDate.of(2023, 10, 11); // A Wednesday
    assertTrue(dayService.isTodayWorkingDay(wednesday), "Wednesday should be a working day.");
  }

  @Test
  void testThursdayIsWorkingDay() {
    LocalDate thursday = LocalDate.of(2023, 10, 12); // A Thursday
    assertTrue(dayService.isTodayWorkingDay(thursday), "Thursday should be a working day.");
  }

  @Test
  void testFridayIsWorkingDay() {
    LocalDate friday = LocalDate.of(2023, 10, 13); // A Friday
    assertTrue(dayService.isTodayWorkingDay(friday), "Friday should be a working day.");
  }

  @Test
  void testNullInputThrowsException() {
    assertThrows(NullPointerException.class, () -> {
      dayService.isTodayWorkingDay(null);
    }, "Providing a null LocalDate should throw a NullPointerException.");
  }
}
