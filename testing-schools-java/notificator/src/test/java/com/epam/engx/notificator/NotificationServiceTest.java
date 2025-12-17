package com.epam.engx.notificator;

import com.epam.engx.notificator.dto.Person;
import com.epam.engx.notificator.dto.UserAction;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Test
    void sendNotifications_shouldNotifyApplicableNotificators() {
        // Arrange
        INotificator notificator1 = mock(INotificator.class);
        INotificator notificator2 = mock(INotificator.class);
        List<INotificator> notificators = List.of(notificator1, notificator2);
        NotificationService notificationService = new NotificationService(notificators);
        Person person = new Person(1L, "Alice");
        UserAction userAction = UserAction.BUILD;

        // Define mock behavior
        when(notificator1.isApplicable(userAction)).thenReturn(true); // notificator1 is applicable
        when(notificator2.isApplicable(userAction)).thenReturn(false); // notificator2 is NOT applicable

        // Act
        notificationService.sendNotifications(person, userAction);

        // Assert
        verify(notificator1, times(1)).isApplicable(userAction);
        verify(notificator1, times(1)).notify(person, userAction); // notificator1.notify() should be called
        verify(notificator2, times(1)).isApplicable(userAction);
        verify(notificator2, never()).notify(person, userAction); // notificator2.notify() should NOT be called
    }

    @Test
    void sendNotifications_shouldNotCallNotifyWhenNoNotificatorsAreApplicable() {
        // Arrange
        INotificator notificator1 = mock(INotificator.class);
        INotificator notificator2 = mock(INotificator.class);
        List<INotificator> notificators = List.of(notificator1, notificator2);
        NotificationService notificationService = new NotificationService(notificators);
        Person person = new Person(2L, "Bob");
        UserAction userAction = UserAction.DEPLOY;

        // Define mock behavior
        when(notificator1.isApplicable(userAction)).thenReturn(false);
        when(notificator2.isApplicable(userAction)).thenReturn(false);

        // Act
        notificationService.sendNotifications(person, userAction);

        // Assert
        verify(notificator1, times(1)).isApplicable(userAction);
        verify(notificator1, never()).notify(person, userAction); // notificator1.notify() should NOT be called
        verify(notificator2, times(1)).isApplicable(userAction);
        verify(notificator2, never()).notify(person, userAction); // notificator2.notify() should NOT be called
    }

    @Test
    void sendNotifications_shouldWorkWithEmptyNotificatorList() {
        // Arrange
        NotificationService notificationService = new NotificationService(List.of()); // No notificators
        Person person = new Person(3L, "Charlie");
        UserAction userAction = UserAction.BUILD;

        // Act
        notificationService.sendNotifications(person, userAction);

        // Assert
        // No mocks are involved, so no interactions to verify.
        // This test simply ensures no exceptions occur when the list is empty.
    }

    @Test
    void sendNotifications_shouldCallNotifyForMultipleApplicableNotificators() {
        // Arrange
        INotificator notificator1 = mock(INotificator.class);
        INotificator notificator2 = mock(INotificator.class);
        List<INotificator> notificators = List.of(notificator1, notificator2);
        NotificationService notificationService = new NotificationService(notificators);
        Person person = new Person(4L, "Daisy");
        UserAction userAction = UserAction.BUILD;

        // Define mock behavior
        when(notificator1.isApplicable(userAction)).thenReturn(true);
        when(notificator2.isApplicable(userAction)).thenReturn(true);

        // Act
        notificationService.sendNotifications(person, userAction);

        // Assert
        verify(notificator1, times(1)).isApplicable(userAction);
        verify(notificator1, times(1)).notify(person, userAction); // notificator1.notify() should be called
        verify(notificator2, times(1)).isApplicable(userAction);
        verify(notificator2, times(1)).notify(person, userAction); // notificator2.notify() should also be called
    }
}