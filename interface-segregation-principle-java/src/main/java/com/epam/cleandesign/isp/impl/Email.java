package com.epam.cleandesign.isp.impl;

import com.epam.cleandesign.isp.api.EmailMessage;
import com.epam.cleandesign.isp.thirdpartyjars.common.Attachment;
import com.epam.cleandesign.isp.thirdpartyjars.common.MessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Email extends AbstractMessage implements EmailMessage {

    private final MessageType type = MessageType.EMAIL;
    private String subject;
    private List<String> ccRecipients = new ArrayList();
    private List<String> bccRecipients = new ArrayList();
    private List<Attachment> attachments = new ArrayList();
    private List<String> recipients = new ArrayList<>();

    public Email(String firstRecipient) {
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
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public List<String> getCcRecipients() {
        return ccRecipients;
    }

    public void setCcRecipients(List<String> ccRecipients) {
        this.ccRecipients = ccRecipients;
    }

    @Override
    public List<String> getBccRecipients() {
        return bccRecipients;
    }

    public void setBccRecipients(List<String> bccRecipients) {
        this.bccRecipients = bccRecipients;
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
        Email that = (Email) o;
        return type == that.type &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(ccRecipients, that.ccRecipients) &&
                Objects.equals(bccRecipients, that.bccRecipients) &&
                Objects.equals(attachments, that.attachments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, subject, ccRecipients, bccRecipients, attachments);
    }
}
