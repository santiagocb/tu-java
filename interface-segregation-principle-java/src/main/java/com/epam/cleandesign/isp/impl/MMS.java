package com.epam.cleandesign.isp.impl;

import com.epam.cleandesign.isp.api.MMSMessage;
import com.epam.cleandesign.isp.thirdpartyjars.common.Attachment;
import com.epam.cleandesign.isp.thirdpartyjars.common.MessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MMS extends AbstractMessage implements MMSMessage {

    private final MessageType type = MessageType.MMS;

    private List<Attachment> attachments = new ArrayList();
    private List<String> recipients = new ArrayList<>();

    public List<String> getRecipients() {
        return recipients;
    }

    public MMS() {
    }

    public MMS(String firstRecipient) {
        recipients.add(firstRecipient);
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
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
        MMS mms = (MMS) o;
        return type == mms.type &&
                Objects.equals(attachments, mms.attachments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, attachments);
    }
}
