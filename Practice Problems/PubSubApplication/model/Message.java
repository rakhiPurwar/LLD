package PubSubApplication.model;

import java.util.Date;

public class Message {
    private final String topicName;
    private final String payload;
    private final long timestamp;

    public Message(String topicName, String paylaod) {
        this.topicName = topicName;
        this.payload = paylaod;
        this.timestamp = System.currentTimeMillis();
    }

    public String getTopicName() {
        return topicName;
    }

    public String getPaylaod() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "topicName='" + topicName + '\'' +
                ", paylaod='" + payload + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

