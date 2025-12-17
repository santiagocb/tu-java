package com.epam.engx.task5;

import com.epam.engx.task5.thirdpartyjar.Book;
import com.epam.engx.task5.thirdpartyjar.BookService;
import com.epam.engx.task5.thirdpartyjar.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.requireNonNull;

public class Library {

    private static final Logger LOGGER = LogManager.getLogger(Library.class);


    private final BookService bookService;

    public Library(BookService bookService) {
        this.bookService = requireNonNull(bookService);
    }

    public void rentBook(Book book, Person person) {
        if (bookService.reachedLimitOfBooks(person)) {
            LOGGER.warn("Limit of books reached for person {}", person);
            return;
        }

        if (!bookService.getAllPersons().contains(person)) {
            LOGGER.info("New person {} is going to rent a book", person);
        }
        bookService.rent(book, person);

    }

}
