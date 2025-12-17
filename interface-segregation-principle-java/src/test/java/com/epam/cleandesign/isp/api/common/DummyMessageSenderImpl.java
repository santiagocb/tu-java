package com.epam.cleandesign.isp.api.common;

import com.epam.cleandesign.isp.api.Message;
import com.epam.cleandesign.isp.api.MessageSender;

public class DummyMessageSenderImpl implements MessageSender {

    @Override
    public void send(Message message) {
        if (message.getType() == null) {
            throw new IllegalArgumentException("Message type needs to be exactly defined");
        }
        System.out.println("Sending email ...");
        System.out.println("Message has been successfully delivered to " + message.getRecipients().toString());
    }
}
