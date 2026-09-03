package splitwise.services;

import splitwise.models.User;
import splitwise.models.splits.Split;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class UserService {
    private final Map<String, User> userMap;

    public UserService() {
        this.userMap = new ConcurrentHashMap<>();
    }

    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    public User getUser(String id) {
      return  userMap.get(id);
    }

    public boolean userExists(String id) {
        return userMap.containsKey(id);
    }
}
