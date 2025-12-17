package com.epam.cleandesign.srp;

import jakarta.mail.Message;
import jakarta.mail.Transport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private ResultSet resultSetMock;

    @BeforeEach
    public void init() throws SQLException {
        MockitoAnnotations.initMocks(this);
        final Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(any())).thenReturn(resultSetMock);
    }

    @Test
    public void emptyJson() throws Exception {
        setUpResultSetMock(Collections.emptyList());
        testJsonConvert("[]");
    }

    @Test
    public void singleEmployeeJson() throws Exception {
        setUpResultSetMock(Collections.singletonList(
                new Employee("Wayne", "Rooney", EmployeeRole.PROJECT_MANAGER, EmployeeSeniority.REGULAR)));
        testJsonConvert(
                "[{\"firstName\":\"Wayne\",\"lastName\":\"Rooney\",\"role\":\"PROJECT_MANAGER\",\"seniority\":\"REGULAR\"}]");
    }

    @Test
    public void multipleEmployeesJson() throws Exception {
        setUpResultSetMock(
                Arrays.asList(new Employee("Wayne", "Rooney", EmployeeRole.SOFTWARE_ENGINEER, EmployeeSeniority.CHIEF),
                        new Employee("Harry", "Kane", EmployeeRole.SOFTWARE_TEST_AUTOMATION_ENGINEER,
                                EmployeeSeniority.JUNIOR
                        )
                ));
        testJsonConvert(
                "[{\"firstName\":\"Wayne\",\"lastName\":\"Rooney\",\"role\":\"SOFTWARE_ENGINEER\",\"seniority\":\"CHIEF\"}," +
                        "{\"firstName\":\"Harry\",\"lastName\":\"Kane\",\"role\":\"SOFTWARE_TEST_AUTOMATION_ENGINEER\",\"seniority\":\"JUNIOR\"}]");
    }

    @Test
    public void sendEmptyEmployeesReport() throws Exception {
        setUpResultSetMock(Collections.emptyList());
        testSendMail("<table>" + "<tr><th>Employee</th><th>Position</th></tr>" + "</table>");
    }

    @Test
    public void sendSingleEmployeeReport() throws Exception {
        setUpResultSetMock(Collections.singletonList(
                new Employee("Wayne", "Rooney", EmployeeRole.RESOURCE_MANAGER, EmployeeSeniority.SENIOR)));
        testSendMail("<table>" + "<tr><th>Employee</th><th>Position</th></tr>" +
                "<tr><td>Wayne Rooney</td><td>SENIOR RESOURCE_MANAGER</td></tr>" + "</table>");
    }

    @Test
    public void sendMultipleEmployeesReport() throws Exception {
        setUpResultSetMock(
                Arrays.asList(new Employee("Wayne", "Rooney", EmployeeRole.SOFTWARE_ENGINEER, EmployeeSeniority.LEAD),
                        new Employee("Harry", "Kane", EmployeeRole.SOFTWARE_TEST_ENGINEER,
                                EmployeeSeniority.JUNIOR
                        )
                ));
        testSendMail("<table>" + "<tr><th>Employee</th><th>Position</th></tr>" +
                "<tr><td>Wayne Rooney</td><td>LEAD SOFTWARE_ENGINEER</td></tr>" +
                "<tr><td>Harry Kane</td><td>JUNIOR SOFTWARE_TEST_ENGINEER</td></tr>" + "</table>");
    }

    private void setUpResultSetMock(List<Employee> employees) throws SQLException {
        AtomicInteger index = new AtomicInteger();
        when(resultSetMock.next())
                .thenAnswer(invocation -> index.get() < employees.size() && index.incrementAndGet() < employees.size());
        lenient().when(resultSetMock.previous()).thenAnswer(invocation -> index.get() >= 0 && index.decrementAndGet() >= 0);
        lenient().when(resultSetMock.isBeforeFirst()).thenAnswer(invocation -> index.get() == -1);
        lenient().when(resultSetMock.first())
                .thenAnswer(invocation -> employees.size() > 0 && index.getAndSet(0) != employees.size());
        lenient().when(resultSetMock.isAfterLast()).thenAnswer(invocation -> index.get() == employees.size());
        lenient().when(resultSetMock.first()).thenAnswer(
                invocation -> employees.size() > 0 && index.getAndSet(employees.size() - 1) != employees.size());

        lenient().when(resultSetMock.getString("FIRST_NAME")).thenAnswer(invocation -> employees.get(index.get()).getFirstName());
        lenient().when(resultSetMock.getString("LAST_NAME")).thenAnswer(invocation -> employees.get(index.get()).getLastName());
        lenient().when(resultSetMock.getString("ROLE")).thenAnswer(invocation -> employees.get(index.get()).getRole() + "");
        lenient().when(resultSetMock.getString("SENIORITY"))
                .thenAnswer(invocation -> employees.get(index.get()).getSeniority() + "");

        index.set(-1);
    }

    private void testSendMail(String expected) throws Exception {
        final ArgumentCaptor<Message> propertiesCaptor = ArgumentCaptor.forClass(Message.class);

        EmployeeRepository repository = new EmployeeRepository(mockConnection);
        EmployeeHTMLFormatter formatter = new EmployeeHTMLFormatter();
        EmployeeReporter employeeReporter = new EmployeeReporter();
        EmployeeService manager = new EmployeeService(repository, formatter, employeeReporter);
        try (MockedStatic<Transport> mockedTransport = Mockito.mockStatic(Transport.class)) {

            manager.sendEmployeesReport();
            mockedTransport.verify(() -> Transport.send(propertiesCaptor.capture()));

            assertEquals(propertiesCaptor.getValue().getContent(), expected);

            //check caching
            clearInvocations(resultSetMock);
            lenient().when(resultSetMock.next()).thenReturn(false);
            manager.sendEmployeesReport();
            assertEquals(propertiesCaptor.getValue().getContent(), expected);
        }

    }

    private void testJsonConvert(String json) throws Exception {
        EmployeeRepository repository = new EmployeeRepository(mockConnection);
        EmployeeJSONFormatter formatter = new EmployeeJSONFormatter();
        String serialized = formatter.formatEmployees(repository.readEmployees());

        JSONAssert.assertEquals(serialized, serialized, json, false);

        //check caching
        clearInvocations(resultSetMock);
        lenient().when(resultSetMock.next()).thenReturn(false);
        serialized = formatter.formatEmployees(repository.readEmployees());
        JSONAssert.assertEquals(serialized, serialized, json, false);
    }
}