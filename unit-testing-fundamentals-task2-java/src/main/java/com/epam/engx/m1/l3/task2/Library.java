package com.epam.engx.m1.l3.task2;

import java.util.HashSet;
import java.util.Set;

class Library {

  private final Set<String> books = new HashSet<>();

  void addBook(String title) {
    if (title == null) {
      throw new NullPointerException("Book title cannot be null.");
    }
    books.add(title);
  }

  void removeBook(String title) {
    if (title == null) {
      throw new NullPointerException("Book title cannot be null.");
    }
    books.remove(title);
  }

  boolean hasBook(String title) {
    if (title == null) {
      throw new NullPointerException("Book title cannot be null.");
    }
    return books.contains(title);
  }
}
