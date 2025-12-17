package com.epam.cleandesign.srp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private List<Employee> employeesInMemory;
    private final Connection connection;

    public EmployeeRepository(Connection connection) {
        this.connection = connection;
        employeesInMemory = null;
    }

    public List<Employee> readEmployees() {
        if (employeesInMemory == null) {
            employeesInMemory = new ArrayList<>();
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM Employees")) {

                while (resultSet.next()) {

                    Employee employee = new Employee();

                    String firstName = resultSet.getString("FIRST_NAME");
                    String lastName = resultSet.getString("LAST_NAME");
                    String role = resultSet.getString("ROLE");
                    String seniority = resultSet.getString("SENIORITY");

                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setRole(EmployeeRole.valueOf(role));
                    employee.setSeniority(EmployeeSeniority.valueOf(seniority));
                    employeesInMemory.add(employee);
                }
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

        return employeesInMemory;
    }
}
