package com.epam.cleandesign.srp;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmployeeReporter {

    public void sendEmployeesEmail(String employeesHtml) {
        String to = "abcd@gmail.com";
        String from = "web@gmail.com";
        String host = "localhost";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Employees report");

            message.setContent(employeesHtml, "text/html; charset=utf-8");
            Transport.send(message);

        } catch (MessagingException e) {
            throw new IllegalStateException(e);
        }
    }
}
