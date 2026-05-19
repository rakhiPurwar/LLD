package NotificationSystem.channel;

import NotificationSystem.Model.Notification;

public class EmailNotificationChannel implements NotificationChannel{
    @Override
    public void send(Notification notification) {
        System.out.println("Sending Email to user: " + notification.getUserId() +
                " with message: " + notification.getMessage());
    }
}
//same task bt differnt bussiness logic -> strategy