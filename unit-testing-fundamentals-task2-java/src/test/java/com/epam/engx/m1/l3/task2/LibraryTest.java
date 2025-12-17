package com.epam.engx.m1.l3.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

  @Test
  void testAddBook() {
    Library library = new Library();

    // Add valid books and verify they are added
    library.addBook("Book One");
    assertTrue(library.hasBook("Book One"), "The library should contain 'Book One' after adding it.");

    library.addBook("Book Two");
    assertTrue(library.hasBook("Book Two"), "The library should contain 'Book Two' after adding it.");
  }

  @Test
  void testAddNullBookThrowsException() {
    Library library = new Library();

    // Adding null should throw NullPointerException
    assertThrows(NullPointerException.class, () -> library.addBook(null),
      "Adding a null book title should throw a NullPointerException.");
  }

  @Test
  void testRemoveBook() {
    Library library = new Library();

    // Add valid book, remove it, and verify it is removed
    library.addBook("Book One");
    library.removeBook("Book One");
    assertFalse(library.hasBook("Book One"), "The library should not contain 'Book One' after removing it.");
  }

  @Test
  void testRemoveNonExistentBook() {
    Library library = new Library();

    // Removing a non-existent book should perform no operation
    library.removeBook("Non-Existent Book");
    assertFalse(library.hasBook("Non-Existent Book"),
      "The library should still not contain 'Non-Existent Book' since it was never added.");
  }

  @Test
  void testRemoveNullBookThrowsException() {
    Library library = new Library();

    // Removing null should throw NullPointerException
    assertThrows(NullPointerException.class, () -> library.removeBook(null),
      "Removing a null book title should throw a NullPointerException.");
  }

  @Test
  void testHasBookOnEmptyLibrary() {
    Library library = new Library();

    // Check if a book exists in an empty library
    assertFalse(library.hasBook("Book One"), "The library should not have 'Book One' if no books have been added.");
  }

  @Test
  void testHasBookAfterAdding() {
    Library library = new Library();

    // Add valid book and verify `hasBook` returns true
    library.addBook("Book One");
    assertTrue(library.hasBook("Book One"), "The library should have 'Book One' after adding it.");
  }

  @Test
  void testHasNullBookThrowsException() {
    Library library = new Library();

    // Checking for null should throw NullPointerException
    assertThrows(NullPointerException.class, () -> library.hasBook(null),
      "Checking for a null book title should throw a NullPointerException.");
  }

  @Test
  void testHasBookCaseSensitivity() {
    Library library = new Library();

    // Add a book with mixed-case letters
    library.addBook("Book One");

    // Ensure case sensitivity ('book one' is treated differently from 'Book One')
    assertTrue(library.hasBook("Book One"), "The library should contain 'Book One' with the exact casing.");
    assertFalse(library.hasBook("book one"), "The library should not contain 'book one' (all lowercase).");
  }

  @Test
  void testAddDuplicateBook() {
    Library library = new Library();

    // Add valid books and ensure duplicates are not added
    library.addBook("Book One");
    library.addBook("Book One"); // Same title added again

    library.addBook("Book Two");

    // Only unique titles should exist in the library
    assertTrue(library.hasBook("Book One"), "The library should contain 'Book One' (only once).");
    assertTrue(library.hasBook("Book Two"), "The library should contain 'Book Two'.");
  }
}
