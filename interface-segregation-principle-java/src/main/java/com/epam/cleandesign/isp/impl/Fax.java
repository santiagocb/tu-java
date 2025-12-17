package com.epam.cleandesign.isp.impl;

import com.epam.cleandesign.isp.api.FaxMessage;
import com.epam.cleandesign.isp.thirdpartyjars.common.Attachment;
import com.epam.cleandesign.isp.thirdpartyjars.common.MessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fax extends AbstractMessage implements FaxMessage {

    private final MessageType type = MessageType.FAX;
    private String companyName;
    private String callbackFax;
    private String subject;

    private List<String> recipients = new ArrayList<>();

    public Fax(String firstRecipient) {
        recipients.add(firstRecipient);
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public List<String> getRecipients() {
        return recipients;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String getCallbackFax() {
        return callbackFax;
    }

    public void setCallbackFax(String callbackFax) {
        this.callbackFax = callbackFax;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
        Fax that = (Fax) o;
        return type == that.type &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(callbackFax, that.callbackFax) &&
                Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, companyName, callbackFax, subject);
    }
}
