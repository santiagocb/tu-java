package com.epam.engx.task5.thirdpartyjar;

import java.util.Collection;

public interface BookService {

    void rent(Book book, Person person);

    boolean reachedLimitOfBooks(Person person);

    Collection<Person> getAllPersons();

}
