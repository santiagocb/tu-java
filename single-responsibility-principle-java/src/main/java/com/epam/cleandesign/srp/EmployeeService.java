package com.epam.cleandesign.srp;


public class EmployeeService {

    EmployeeRepository employeeRepository;
    EmployeeHTMLFormatter employeeFormatter;
    EmployeeReporter employeeReporter;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeHTMLFormatter employeeFormatter, EmployeeReporter employeeReporter) {
        this.employeeRepository = employeeRepository;
        this.employeeFormatter = employeeFormatter;
        this.employeeReporter = employeeReporter;
    }

    public void sendEmployeesReport() {
        employeeReporter.sendEmployeesEmail(employeeFormatter.formatEmployees(employeeRepository.readEmployees()));
    }
}
