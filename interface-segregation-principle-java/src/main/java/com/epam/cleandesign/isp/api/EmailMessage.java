package com.epam.cleandesign.isp.api;

import com.epam.cleandesign.isp.thirdpartyjars.common.Attachment;

import java.util.List;

public interface EmailMessage extends Message {

    String getSubject();

    List<Attachment> getAttachments();

    List<String> getCcRecipients();

    List<String> getBccRecipients();
}
