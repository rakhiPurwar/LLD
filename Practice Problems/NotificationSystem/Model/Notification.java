package NotificationSystem.Model;

public class Notification {
    private final String userId;
    private final String message;

    public Notification(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public String getUserId() {  //only getters as final
        return userId;
    }

    public String getMessage() {  //only getters as final
        return message;
    }
}
