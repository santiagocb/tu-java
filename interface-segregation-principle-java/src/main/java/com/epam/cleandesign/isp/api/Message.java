package com.epam.cleandesign.isp.api;

import com.epam.cleandesign.isp.thirdpartyjars.common.MessageType;

import java.util.List;

public interface Message {

    List<String> getRecipients();

    String getMessageBody();

    MessageType getType();
}
