package com.epam.engx.task4;

import com.epam.engx.task4.thirdpartyjar.Car;
import com.epam.engx.task4.thirdpartyjar.CarService;
import com.epam.engx.task4.thirdpartyjar.UnavailableCarException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.requireNonNull;

public class CarRental {

    private static final Logger LOGGER = LogManager.getLogger(CarRental.class);


    private final CarService carService;

    public CarRental(CarService carService) {
        this.carService = requireNonNull(carService);
    }

    public void rent(Car car) {
        try {
            carService.validate(car);
            carService.book(car);
            LOGGER.info("Car {} booked successfully", car);
        } catch (UnavailableCarException exception) {
            LOGGER.info("Booking car {} was not possible", car, exception);
        }
    }

}
