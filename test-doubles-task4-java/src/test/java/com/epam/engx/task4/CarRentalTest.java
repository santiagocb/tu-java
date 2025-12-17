package com.epam.engx.task4;

import com.epam.engx.task4.thirdpartyjar.Car;
import com.epam.engx.task4.thirdpartyjar.CarService;
import com.epam.engx.task4.thirdpartyjar.UnavailableCarException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.Logger;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CarRentalTest {

    private CarService carServiceMock;
    private CarRental carRental;
    private Logger loggerMock;

    @BeforeEach
    void setUp() {
        carServiceMock = mock(CarService.class);
        carRental = new CarRental(carServiceMock);
        loggerMock = mock(Logger.class); // Simulated logger (requires integration steps if verifying logs)
    }

    @Test
    void constructor_shouldThrowExceptionWhenCarServiceIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> new CarRental(null));
    }

    @Test
    void rent_shouldBookCarSuccessfullyWhenCarIsAvailable() throws Exception {
        // Arrange
        Car car = new Car(1L, "Tesla");
        doNothing().when(carServiceMock).validate(car);
        doNothing().when(carServiceMock).book(car);

        // Act
        carRental.rent(car);

        // Assert
        verify(carServiceMock).validate(car);
        verify(carServiceMock).book(car);
    }

    @Test
    void rent_shouldNotBookCarWhenUnavailableCarExceptionOccurs() throws Exception {
        // Arrange
        Car car = new Car(1L, "Tesla");
        doThrow(new UnavailableCarException()).when(carServiceMock).validate(car);

        // Act
        carRental.rent(car);

        // Assert
        verify(carServiceMock).validate(car);
        verify(carServiceMock, never()).book(car); // Ensure book is not called
    }
}