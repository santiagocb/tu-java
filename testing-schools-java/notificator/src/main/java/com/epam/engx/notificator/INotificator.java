package com.epam.engx.notificator;

import com.epam.engx.notificator.dto.Person;
import com.epam.engx.notificator.dto.UserAction;

public interface INotificator {

    void notify(Person person, UserAction userAction);

    boolean isApplicable(UserAction userAction);
}
