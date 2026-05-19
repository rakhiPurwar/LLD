package NotificationSystem.service;

import NotificationSystem.Model.ChannelType;
import NotificationSystem.Model.Notification;
import NotificationSystem.Model.UserPreference;
import NotificationSystem.channel.NotificationChannel;
import NotificationSystem.factory.NotificationChannelFactory;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationDispatcher {
    private final UserPreferenceService userPreferenceService;

    public NotificationDispatcher(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    public void dispatch(Notification notification) {
        UserPreference preference = userPreferenceService.getPreference(notification.getUserId());
        Set<ChannelType> channels = preference.getPreferredChannels();
        for (ChannelType channelType : channels) {
            NotificationChannel channel = NotificationChannelFactory.getChannel(channelType);
            channel.send(notification);
        }
    }

    public static class NotificationService {
        private final NotificationDispatcher notificationDispatcher;

        public NotificationService(NotificationDispatcher notificationDispatcher) {
            this.notificationDispatcher = notificationDispatcher;
        }

        public void sendNotification(Notification notification){
            notificationDispatcher.dispatch(notification);
        }
    }

    public static class AsyncNotificationService {
        private final NotificationDispatcher dispatcher;
        private final ExecutorService executorService;


        public AsyncNotificationService(NotificationDispatcher dispatcher, ExecutorService executorService) {
            this.dispatcher = dispatcher;
            this.executorService = Executors.newFixedThreadPool(10);
        }

        public void sendNotification( Notification notification){
            executorService.submit(() -> dispatcher.dispatch(notification));
        }
    }
}
