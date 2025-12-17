package com.epam.cleandesign.srp;

import java.util.List;

public class EmployeeHTMLFormatter implements EmployeeFormatter {

    @Override
    public String formatEmployees(List<Employee> employees) {

        StringBuilder builder = new StringBuilder();
        builder.append("<table>").append("<tr><th>Employee</th><th>Position</th></tr>");

        for (Employee employee : employees) {
            builder.append("<tr><td>").append(employee.getFirstName()).append(" ").append(employee.getLastName())
                    .append("</td><td>").append(employee.getSeniority()).append(" ").append(employee.getRole())
                    .append("</td></tr>");
        }

        builder.append("</table>");

        return builder.toString();
    }
}
