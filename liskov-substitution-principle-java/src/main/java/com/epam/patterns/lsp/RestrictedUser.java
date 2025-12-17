package com.epam.patterns.lsp;

import java.util.Set;

class RestrictedUser implements User {
    private PowerUser powerUser;
    private Set<String> protectedRights;

    RestrictedUser(PowerUser powerUser, Set<String> protectedRights) {
        this.powerUser = powerUser;
        this.protectedRights = protectedRights;
    }

    @Override
    public void setupAccessRight(String right, boolean value) {
        if (!protectedRights.contains(right)) {
            powerUser.setupAccessRight(right, value);
        }
    }

    @Override
    public boolean getValueOfAccessRight(String right) {
        return powerUser.getValueOfAccessRight(right);
    }
}
