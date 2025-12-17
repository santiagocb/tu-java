package com.epam.cleandesign.srp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class EmployeeJSONFormatter implements EmployeeFormatter {

    @Override
    public String formatEmployees(List<Employee> employees) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(employees);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
