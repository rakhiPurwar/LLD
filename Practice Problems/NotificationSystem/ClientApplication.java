package NotificationSystem;

import NotificationSystem.Model.Notification;
import NotificationSystem.Model.UserPreference;
import NotificationSystem.service.NotificationDispatcher;
import NotificationSystem.service.NotificationService;
import NotificationSystem.service.UserPreferenceService;

import java.util.Set;

import static NotificationSystem.Model.ChannelType.EMAIL;
import static NotificationSystem.Model.ChannelType.PUSH;

public class ClientApplication {

    public static void main(String args[]){
        UserPreference userPreference = new UserPreference("user123", Set.of(EMAIL,PUSH));
        UserPreferenceService userPreferenceService  = new UserPreferenceService();
        userPreferenceService.savePreference(userPreference);

        Notification notification = new
                Notification("user123","Your order otw");

        NotificationDispatcher notificationDispatcher = new NotificationDispatcher(userPreferenceService);


        NotificationService service = new NotificationService(notificationDispatcher);
        NotificationApplication notificationApplication = new NotificationApplication(service);
        notificationApplication.notify(notification);

    }
}
