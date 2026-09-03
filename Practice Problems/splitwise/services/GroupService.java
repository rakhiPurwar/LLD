package splitwise.services;

import PubSubApplication.publisher.Publisher;
import splitwise.models.Group;
import splitwise.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroupService {
    private final Map<String, Group> groupMap;

    public GroupService() {
        groupMap = new ConcurrentHashMap<>();
    }

    public Group getGroup(String id) {
        return groupMap.get(id);
    }

    public void createGroup(String id, String name, String description) {
        groupMap.put(id, new Group(id,name,description));
    }

    public boolean containsGroup(String id) {
        return groupMap.containsKey(id);
    }

    public void addUserToGroup(String id, User user) {
        Group group = groupMap.get(id);
        if(group!=null && user!=null) {
            group.addMember(user);
        }
    }
}
