package NotificationSystem.Model;

import java.util.Collections;
import java.util.Set;

//immutable object
public class UserPreference {
    //immutable objects
    private final String userId;
    private final Set<ChannelType> preferredChannels;

    public UserPreference(String userId, Set<ChannelType> preferredChannels) {
        this.userId = userId;
        this.preferredChannels = preferredChannels;
    }

    public String getUserId() {
        return userId;
    }

    public Set<ChannelType> getPreferredChannels() {
        return Set.copyOf(preferredChannels);
    }
}
