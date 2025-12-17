package com.epam.engx.task5.thirdpartyjar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class DefaultBookService implements BookService {

    private static final Logger LOGGER = LogManager.getLogger(DefaultBookService.class);

    private static final int RENTS_LIMIT = 3;

    private final Map<Person, Integer> counter = new HashMap<>();

    @Override
    public void rent(Book book, Person person) {
        // do some renting logic
        LOGGER.info("Book {} rented", book);
        counter.put(person, counter.getOrDefault(person, 0) + 1);
    }

    @Override
    public boolean reachedLimitOfBooks(Person person) {
        var booksRented = counter.get(person);
        return nonNull(booksRented) && booksRented >= RENTS_LIMIT;
    }

    @Override
    public Collection<Person> getAllPersons() {
        return counter.keySet();
    }
}
