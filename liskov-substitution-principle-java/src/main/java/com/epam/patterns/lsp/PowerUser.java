package com.epam.patterns.lsp;

import java.util.HashMap;
import java.util.Map;

class PowerUser implements User {

    private Map<String, Boolean> accessRights = new HashMap<>();

    @Override
    public void setupAccessRight(String right, boolean value) {
        accessRights.put(right, value);
    }

    @Override
    public boolean getValueOfAccessRight(String right) {
        return accessRights.getOrDefault(right, false);
    }
}
