package NotificationSystem;

import NotificationSystem.Model.Notification;
import NotificationSystem.service.NotificationService;

public class NotificationApplication {
    private final NotificationService notificationService;

    public NotificationApplication(NotificationService notificationService) {
        this.notificationService = notificationService;

    }

    public void notify(Notification notification) {
       notificationService.sendNotification(notification);
    }
}

//Customer
//   ↓
//NotificationApplication
//   ↓
//NotificationService
//   ↓
//NotificationDispatcher
//   ↓
//UserPreferenceService
//   ↓
//NotificationChannelFactory
//   ↓
//Email/SMS/Push Channel