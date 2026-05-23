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
}
