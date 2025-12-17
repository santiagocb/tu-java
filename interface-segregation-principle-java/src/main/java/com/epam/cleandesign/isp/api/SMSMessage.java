package com.epam.cleandesign.isp.api;

import java.util.List;

public interface SMSMessage extends Message {


    List<String> getRecipients();
}
