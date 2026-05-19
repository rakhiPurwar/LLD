package NotificationSystem.channel;

import NotificationSystem.Model.Notification;

public class SmsNotificationChannel implements NotificationChannel{
    @Override
    public void send(Notification notification) {
        System.out.println("Sending SMS to user: " + notification.getUserId() +
                " with message: " + notification.getMessage());
    }
}
