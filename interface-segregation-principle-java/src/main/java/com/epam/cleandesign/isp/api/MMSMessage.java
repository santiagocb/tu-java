package com.epam.cleandesign.isp.api;

import com.epam.cleandesign.isp.thirdpartyjars.common.Attachment;
import com.epam.cleandesign.isp.thirdpartyjars.common.MessageType;

import java.util.List;

public interface MMSMessage extends Message {


    List<Attachment> getAttachments();

    MessageType getType();
}
