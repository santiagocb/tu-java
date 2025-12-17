package com.epam.engx.task1;

import com.epam.engx.task1.thirdpartyjar.Logger;
import com.epam.engx.task1.thirdpartyjar.Person;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NamesCollectorTest {

    @Test
    void collectNames_shouldReturnListOfNamesAndLogIt() {
        // Arrange: Create Logger Mock
        Logger mockLogger = Mockito.mock(Logger.class);

        // Arrange: Create test Persons
        Person person1 = new Person(1L, "Alice");
        Person person2 = new Person(2L, "Bob");

        Set<Person> persons = Set.of(person1, person2);

        NamesCollector namesCollector = new NamesCollector(mockLogger);

        // Act: Call the method
        List<String> result = namesCollector.collectNames(persons);

        // Assert: Verify the returned list (order-independent comparison)
        assertEquals(Set.of("Alice", "Bob"), Set.copyOf(result));

        // Assert: Verify Logger was called with the correct argument
        ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockLogger, times(1)).log(captor.capture());
        assertEquals(Set.of("Alice", "Bob"), Set.copyOf(captor.getValue()));
    }

    @Test
    void collectNames_shouldHandleEmptySet() {
        // Arrange: Create Logger Mock
        Logger mockLogger = Mockito.mock(Logger.class);

        // Arrange: Empty set of persons
        Set<Person> persons = Set.of();

        NamesCollector namesCollector = new NamesCollector(mockLogger);

        // Act: Call the method
        List<String> result = namesCollector.collectNames(persons);

        // Assert: Verify the returned list is empty
        assertEquals(List.of(), result);

        // Assert: Verify Logger was called with an empty list
        ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockLogger, times(1)).log(captor.capture());
        assertEquals(List.of(), captor.getValue());
    }

    @Test
    void collectNames_shouldThrowExceptionForNullLogger() {
        // Act & Assert: Try creating NamesCollector with a null Logger
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new NamesCollector(null));

        // Assert: Verify that exception message is correct
        assertEquals("Logger cannot be null", exception.getMessage());
    }

    @Test
    void collectNames_shouldThrowExceptionForNullPersonsSet() {
        // Arrange: Create Logger Mock
        Logger mockLogger = Mockito.mock(Logger.class);

        NamesCollector namesCollector = new NamesCollector(mockLogger);

        // Act & Assert: Pass null as the persons parameter
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> namesCollector.collectNames(null));

        // Assert: Verify that exception message is correct
        assertEquals("Persons set cannot be null", exception.getMessage());
    }
}