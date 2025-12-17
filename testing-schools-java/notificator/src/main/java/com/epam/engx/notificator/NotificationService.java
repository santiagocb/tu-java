package com.epam.engx.notificator;

import java.util.List;

import com.epam.engx.notificator.dto.Person;
import com.epam.engx.notificator.dto.UserAction;

public class NotificationService {

    private final List<INotificator> notificators;


    public NotificationService(List<INotificator> notificators) {
        this.notificators = notificators;
    }


    public void sendNotifications(Person person, UserAction userAction) {
        notificators.stream()
                .filter(notificator -> notificator.isApplicable(userAction))
                .forEach(notificator -> notificator.notify(person, userAction));
    }
}
