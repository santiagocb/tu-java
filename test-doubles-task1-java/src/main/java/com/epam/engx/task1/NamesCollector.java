package com.epam.engx.task1;

import com.epam.engx.task1.thirdpartyjar.Logger;
import com.epam.engx.task1.thirdpartyjar.Person;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class NamesCollector {

    private final Logger logger;

    public NamesCollector(Logger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger cannot be null");
        }
        this.logger = requireNonNull(logger);
    }

    public List<String> collectNames(Set<Person> persons) {
        if (persons == null) {
            throw new IllegalArgumentException("Persons set cannot be null");
        }

        var names = persons.stream()
                .map(Person::name)
                .toList();

        logger.log(names);

        return names;
    }

}
