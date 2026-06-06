package PubSubApplication.core;

import PubSubApplication.model.Message;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//central registry
//topic creation, get topic, subscribe unsubscribe, notify
public class MessageBroker {
    //singleton, thread safe via class loader
    private static final MessageBroker INSTANCE = new MessageBroker();
    private MessageBroker() {}
    public static MessageBroker getInstance() {return INSTANCE;}

    //==STATE==
    private final Map<String,Topic> topics = new ConcurrentHashMap<>();

    public Topic createTopic(String topicName){
        //put if absent is atomic -> no duplicate topic will be created if multiple threads try to create the same topic at the same time.
        topics.putIfAbsent(topicName, new Topic(topicName));
        return topics.get(topicName);
    }

    public Topic getTopic(String name){
        return topics.get(name);
    }

    public void subscribe(String topicName, Subscriber subscriber){
        Topic topic = getOrThrow(topicName);
        topic.subscribe(subscriber);
    }

    public void unsubscribe(String topicName, Subscriber subscriber){
        Topic topic = getOrThrow(topicName);
        topic.unsubscribe(subscriber);
    }

    private Topic getOrThrow(String topicName) {
        Topic t = topics.get(topicName);
        if(t == null){
            throw new IllegalArgumentException("Topic " + topicName + " does not exist.");
        }
        return t;
    }

    public void publish(String topicName, String payload){
        Topic topic = getOrThrow(topicName);
        Message message = new Message(topicName,payload);
        System.out.println("Publishing message: " + message);
        topic.notifySubscriber(message);

    }
}
