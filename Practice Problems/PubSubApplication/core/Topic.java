package PubSubApplication.core;

import PubSubApplication.model.Message;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Topic {
    private final String name;
    private final List<Subscriber> subscribers;


    public  Topic(String name) {
        this.name = name;
        subscribers = new CopyOnWriteArrayList<>();
    }

    public String getName(){
        return name;
    }

    //not thread safe when multiple people are subscribing or unsubscribing at the same time.
    // We can use CopyOnWriteArrayList to make it thread safe.
    //synchronised also on this method as well but that will block the thread and reduce the throughput of the system.
    //but here updates wont be frequent so we can use synchronised
    public synchronized void subscribe(Subscriber subscriber){
        if(!subscribers.contains(subscriber)){
            subscribers.add(subscriber);
            System.out.println("Subscriber " + subscriber+ " subscribed to topic " + name);
        }
    }

     public synchronized void unsubscribe(Subscriber subscriber){
        if(subscribers.contains(subscriber)){
            subscribers.remove(subscriber);
            System.out.println("Subscriber " + subscriber+ " unsubscribed from topic " + name);
        }
    }

    //only read is happening. no modification and also using copyonwrotearraylist , read are thread safe
  // use executor service to have async processing
    public void notifySubscriber(Message message){
        for (Subscriber subscriber : subscribers) { //safe snapshot iteration
            try {
                subscriber.onMessage(message);
            } catch (Exception e) {
                //reliability if some sub fails, -> retry, dead letter q
                System.err.println("Error notifying subscriber: " + e.getMessage());
            }
        }
    }

    public int subscriberCount(){
        return subscribers.size();
    }
}
