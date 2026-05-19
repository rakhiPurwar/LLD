package NotificationSystem.service;

import NotificationSystem.Model.ChannelType;
import NotificationSystem.Model.UserPreference;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserPreferenceService {
    private final Map<String, UserPreference> preferenceMap = new ConcurrentHashMap<>();


    /// we could have used synchronised also, bt => synch blocks/locks the whole object, now if 1 thread is doing write and 99 threads come
    /// they would be blocked, which would slow down the system, 99 threas blocked evn if they are trying to access some dfferent value.
    public void savePreference(UserPreference preference){
        preferenceMap.put(preference.getUserId(), preference);
    }

    public UserPreference getPreference(String userId){
        return preferenceMap.getOrDefault(
                userId,new UserPreference(userId, Set.of(ChannelType.EMAIL))
        );
    }

}
