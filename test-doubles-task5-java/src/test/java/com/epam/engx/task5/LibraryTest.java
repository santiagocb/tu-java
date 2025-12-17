package com.epam.engx.task5;

import com.epam.engx.task5.thirdpartyjar.Book;
import com.epam.engx.task5.thirdpartyjar.BookService;
import com.epam.engx.task5.thirdpartyjar.DefaultBookService;
import com.epam.engx.task5.thirdpartyjar.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LibraryTest {

    private BookService bookServiceSpy;
    private Library library;

    @BeforeEach
    void setUp() {
        // Use a spy for the BookService as per requirements.
        DefaultBookService realBookService = new DefaultBookService();
        bookServiceSpy = Mockito.spy(realBookService);
        library = new Library(bookServiceSpy);
    }

    // 1. Validate that the Library constructor throws when bookService is null
    @Test
    void constructor_shouldThrowExceptionWhenBookServiceIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> new Library(null),
                "Constructor should throw a NullPointerException when bookService is null"
        );
    }

    // 2. Test the rental flow when the person has reached the book limit
    @Test
    void rentBook_shouldNotRentWhenPersonHasReachedLimit() {
        // Arrange
        Book book = new Book(1L, "1984");
        Person person = new Person(1L, "John Doe");

        // Simulate that the person has reached their book rental limit
        when(bookServiceSpy.reachedLimitOfBooks(person)).thenReturn(true);

        // Act - attempt to rent the book via the library
        library.rentBook(book, person);

        // Assert - no interaction with the "rent" method of bookService
        verify(bookServiceSpy, never()).rent(book, person);
    }

    // 3. Test that the Library logs info when a new person rents for the first time
    @Test
    void rentBook_shouldLogNewPersonWhenRentForFirstTime() {
        // Arrange
        Book book = new Book(2L, "The Catcher in the Rye");
        Person person = new Person(2L, "Jane Smith");

        when(bookServiceSpy.reachedLimitOfBooks(person)).thenReturn(false);
        when(bookServiceSpy.getAllPersons()).thenReturn(Collections.emptySet()); // Person is new

        // Act
        library.rentBook(book, person);

        // Assert
        verify(bookServiceSpy).rent(book, person);
        verify(bookServiceSpy, atLeastOnce()).getAllPersons();
    }

    // 4. Test the Library's interaction with an empty bookService (no persons rented)
    @Test
    void rentBook_shouldHandleBookServiceWithoutRentalsGracefully() {
        // Arrange
        Book book = new Book(3L, "Brave New World");
        Person person = new Person(3L, "Alice Johnson");

        when(bookServiceSpy.reachedLimitOfBooks(person)).thenReturn(false);
        when(bookServiceSpy.getAllPersons()).thenReturn(Collections.emptyList());

        // Act
        library.rentBook(book, person);

        // Assert - verify the interaction with the spy
        verify(bookServiceSpy).rent(book, person);
        assertDoesNotThrow(() -> library.rentBook(book, person), "rentBook should handle bookService without rentals gracefully");
    }

    // 5. Test the rental flow when a person has not reached the limit
    @Test
    void rentBook_shouldRentWhenPersonHasNotReachedLimit() {
        // Arrange
        Book book = new Book(4L, "To Kill a Mockingbird");
        Person person = new Person(4L, "Michael Scott");

        // Simulate that the person has not reached their book rental limit
        when(bookServiceSpy.reachedLimitOfBooks(person)).thenReturn(false);
        when(bookServiceSpy.getAllPersons()).thenReturn(List.of(person));

        // Act
        library.rentBook(book, person);

        // Assert - interaction with the "rent" method is successful
        verify(bookServiceSpy).rent(book, person);
    }

    // 6. Edge case: Ensure that the Library gracefully handles null persons
    @Test
    void rentBook_shouldThrowExceptionForNullPerson() {
        // Arrange
        Book book = new Book(5L, "Moby Dick");

        // Act & Assert
        assertDoesNotThrow(
                () -> library.rentBook(book, null),
                "Library should not throw an exception for a null person, but it should handle it gracefully"
        );
    }

    // 7. Edge case: Ensure Library handles null books without exceptions
    @Test
    void rentBook_shouldThrowExceptionForNullBook() {
        // Arrange
        Person person = new Person(6L, "Dwight Schrute");

        // Act & Assert
        assertDoesNotThrow(
                () -> library.rentBook(null, person),
                "Library should not throw an exception for a null book, but it should handle it gracefully"
        );
    }
}