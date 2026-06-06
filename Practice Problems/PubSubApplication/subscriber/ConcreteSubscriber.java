package PubSubApplication.subscriber;

import PubSubApplication.core.Subscriber;
import PubSubApplication.model.Message;


//a real subscriber
public class ConcreteSubscriber implements Subscriber {
    private final String name;

    public ConcreteSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Subscriber " + name + " received message: " + message);
    }

    @Override
    public String toString() {
        return name;
    }
}
