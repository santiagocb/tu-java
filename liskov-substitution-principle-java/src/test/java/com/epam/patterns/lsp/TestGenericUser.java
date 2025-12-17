package com.epam.patterns.lsp;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static com.epam.patterns.lsp.DatabaseGateway.WRITE_TO_DATABASE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGenericUser {

    @Test
    public void shouldNotGetValueOfAccessRightWhenRightIsProtected() {
        HashSet<String> protectedRights = new HashSet<>();
        protectedRights.add(WRITE_TO_DATABASE);

        PowerUser powerUser = new PowerUser();
        RestrictedUser restrictedUser = new RestrictedUser(powerUser, protectedRights);
        restrictedUser.setupAccessRight(DatabaseGateway.WRITE_TO_DATABASE, true);

        assertFalse(restrictedUser.getValueOfAccessRight(WRITE_TO_DATABASE));
    }

    @Test
    public void shouldGetValueOfAccessRightWhenRightIsNotProtected() {
        final String CHANGE_IN_DATABASE = "change in database";

        PowerUser powerUser = new PowerUser();
        RestrictedUser restrictedUser = new RestrictedUser(powerUser, new HashSet<>());

        restrictedUser.setupAccessRight(CHANGE_IN_DATABASE, true);

        assertTrue(restrictedUser.getValueOfAccessRight(CHANGE_IN_DATABASE));
    }

}
