package com.epam.patterns.lsp;

import org.junit.jupiter.api.Test;

import static com.epam.patterns.lsp.DatabaseGateway.readFromDB;
import static com.epam.patterns.lsp.DatabaseGateway.writeToDBForce;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDBAccess {

    @Test
    public void testPowerUser() {

        PowerUser powerUser = new PowerUser();

        writeToDBForce(powerUser, "payload");
        assertEquals(readFromDB(), "payload");
    }

}
