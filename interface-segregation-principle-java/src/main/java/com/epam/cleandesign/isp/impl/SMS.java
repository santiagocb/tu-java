package com.epam.cleandesign.isp.impl;

import com.epam.cleandesign.isp.api.Message;
import com.epam.cleandesign.isp.api.SMSMessage;
import com.epam.cleandesign.isp.thirdpartyjars.common.Attachment;
import com.epam.cleandesign.isp.thirdpartyjars.common.MessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SMS extends AbstractMessage implements SMSMessage {

    private final MessageType type = MessageType.SMS;
    private List<String> recipients = new ArrayList<>();

    public SMS(String firstRecipient) {
        recipients.add(firstRecipient);
    }


    @Override
    public List<String> getRecipients() {
        return recipients;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SMS sms = (SMS) o;
        return type == sms.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
