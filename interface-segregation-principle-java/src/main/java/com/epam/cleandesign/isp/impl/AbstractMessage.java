package com.epam.cleandesign.isp.impl;

import com.epam.cleandesign.isp.api.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractMessage implements Message {

    protected List<String> recipients = new ArrayList<>();
    protected String messageBody;

    @Override
    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractMessage that = (AbstractMessage) o;

        return Objects.equals(recipients, that.recipients) &&
                Objects.equals(messageBody, that.messageBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipients, messageBody);
    }
}
