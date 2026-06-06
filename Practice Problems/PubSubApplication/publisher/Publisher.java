package PubSubApplication.publisher;

import PubSubApplication.core.MessageBroker;
import PubSubApplication.model.Message;

public class Publisher {
    private final String name;;
    private final MessageBroker broker;

    public Publisher(String name){
        this.name = name;
        this.broker = MessageBroker.getInstance();
    }

    public void publisg(String topicName, String payload){
        System.out.println("Publisher " + name + " is publishing message to topic " + topicName + " with payload: " + payload);
        broker.publish(topicName, payload);
    }



}
