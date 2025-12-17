package com.epam.cleandesign.isp.api;

public interface FaxMessage extends Message {

    String getCompanyName();

    String getCallbackFax();

    String getSubject();
}
