package com.epam.cleandesign;

import com.epam.cleandesign.domain.Customer;
import com.epam.cleandesign.domain.CustomerRepository;
import com.epam.cleandesign.exceptions.NotEligibleForMortgageException;
import com.epam.cleandesign.exceptions.WrongDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MortgageApplicationQueueProcessorTest {

    private static final double DELTA = 0.0001;

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void validateHappinessPath(int customerId, double balance, int badCreditHistoryCount, double amountRequested,
                                      double expected) {
        Customer customer = new Customer(customerId, "first", "last", balance, (byte) badCreditHistoryCount);

        CustomerRepository customerRepositoryMock = mock(CustomerRepository.class);
        when(customerRepositoryMock.get(customerId)).thenReturn(customer);

        process(customerId, amountRequested, customerRepositoryMock);

        assertEquals(expected, customer.getBalance(), DELTA);
    }

    @Test
    public void validateUnhappinessPath() {
        int customerId = 1000;
        double amountRequested = 1500;

        CustomerRepository customerRepositoryMock = mock(CustomerRepository.class);
        when(customerRepositoryMock.get(customerId)).thenReturn(null);

        assertThrows(WrongDataException.class, () -> process(customerId, amountRequested, customerRepositoryMock));
    }

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1, 1000d, 0, 500d, 1500d),
                Arguments.of(2, 240d, 0, 100d, 340d),
                Arguments.of(3, 0d, 0, 400d, 0d),
                Arguments.of(4, 500d, 1, 1000d, 500d)
        );
    }

    private void process(int customerId, double amountRequested, CustomerRepository customerRepositoryMock) {
        MortgageApplicationQueueProcessor processor = new MortgageApplicationQueueProcessor(customerRepositoryMock);
        try {
            processor.processRequest(customerId, amountRequested);
        } catch (NotEligibleForMortgageException e) {

        }
    }
}