package PubSubApplication.publisher;

import PubSubApplication.core.MessageBroker;

public class Publisher {
    private final String name;;
    private final MessageBroker broker;

    public Publisher(String name){
        this.name = name;
        this.broker = MessageBroker.getInstance();
    }

    public void publish(String topicName, String payload){
        System.out.println("Publisher " + name + " is publishing message to topic " + topicName + " with payload: " + payload);
        broker.publish(topicName, payload);
    }



}
