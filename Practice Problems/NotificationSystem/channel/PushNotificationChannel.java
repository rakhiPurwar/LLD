package NotificationSystem.channel;

import NotificationSystem.Model.Notification;

public class PushNotificationChannel implements NotificationChannel{
    @Override
    public void send(Notification notification) {
        System.out.println("Sending Push Notification to user: " + notification.getUserId()+
                " with message: " + notification.getMessage());
    }
}
