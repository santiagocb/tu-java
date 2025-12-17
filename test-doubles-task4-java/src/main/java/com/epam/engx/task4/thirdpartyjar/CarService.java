package com.epam.engx.task4.thirdpartyjar;

public interface CarService {

    void validate(Car car) throws UnavailableCarException;

    void book(Car car);

}
