package com.epam.patterns.lsp;

public interface User {
    void setupAccessRight(String right, boolean value);
    boolean getValueOfAccessRight(String right);
}
