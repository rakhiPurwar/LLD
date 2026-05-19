package NotificationSystem.channel;

import NotificationSystem.Model.Notification;

public interface NotificationChannel {
    void send(Notification notification);
}
