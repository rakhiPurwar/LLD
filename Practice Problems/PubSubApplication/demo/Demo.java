package PubSubApplication.demo;

import PubSubApplication.core.MessageBroker;
import PubSubApplication.core.Subscriber;
import PubSubApplication.publisher.Publisher;
import PubSubApplication.subscriber.ConcreteSubscriber;

public class Demo {
    public static void main(String[] args) {
        MessageBroker broker =MessageBroker.getInstance();

        //topic creation
        broker.createTopic("Tech");
        broker.createTopic("Sport");


        /// subscriber creation
        Subscriber Alice = new ConcreteSubscriber("Alice");
        Subscriber Bob = new ConcreteSubscriber("Bob");
        Subscriber Charlie = new ConcreteSubscriber("Charlie");

        /// subscribe
        broker.subscribe("Tech", Alice);
        broker.subscribe("Tech", Bob);
        broker.subscribe("Sport", Charlie);
        broker.subscribe("Sport",Alice);

        /// publish

        Publisher espn =  new Publisher("ESPN");
        Publisher techCrunch = new Publisher("TechCrunch");

        espn.publish("Sport", "Team A won the match!");
        techCrunch.publish("Tech", "New smartphone released!");


    }
}
//design durable as easy to add subs, and make it aysnc
