package NotificationSystem.service;

import NotificationSystem.Model.Notification;//only provide methods , should be immutable
public class NotificationService {

        private final NotificationDispatcher notificationDispatcher;

        public NotificationService(NotificationDispatcher notificationDispatcher) {
            this.notificationDispatcher = notificationDispatcher;
        }

        public void sendNotification(Notification notification){
            notificationDispatcher.dispatch(notification);
        }
    }