package SimpleFactory;
/*The Factory Pattern is a creational design pattern that
provides an interface for creating objects, but lets subclasses or a separate method decide which class to instantiate.
public void send(String type) {
    if(type.equals("EMAIL")) {
        SimpleFactory.EmailNotification email = new SimpleFactory.EmailNotification();
        email.send();
    }
    else if(type.equals("SMS")) {
        SimpleFactory.SmsNotification sms = new SimpleFactory.SmsNotification();
        sms.send();
    }
    else if(type.equals("PUSH")) {
        SimpleFactory.PushNotification push = new SimpleFactory.PushNotification();
        push.send();
    }
}


 */

interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending PUSH: " + message);
    }
}

class NotificationFactory{
    public static Notification createNotification(String type){
        if(type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        switch (type.toUpperCase()) {
            case "EMAIL":
                return new EmailNotification();

            case "SMS":
                return new SmsNotification();

            case "PUSH":
                return new PushNotification();

            default:
                throw new IllegalArgumentException("Unknown notification type");
        }
    }

}

class NotificationService {
   public void sendNotification(String type, String message) {
       Notification notification = NotificationFactory.createNotification(type);
       notification.send(message);
   }
}

class FactoryPattern {
   public static void main(String[] args){
       NotificationService service = new NotificationService();
       service.sendNotification("EMAIL","send email notifiication");
   }

}

/*
Before:

Client handled object creation.

After:

Client only:

Asks factory

Uses interface

Client DOES NOT know:

Which class is created

How it is created''Where To Use Factory Pattern?

Use it when:

✔ Multiple implementations exist

Payment methods

SimpleFactory.Notification types

File parsers

Database connectors

✔ Creation logic is complex

Maybe:

Reads config

Checks country

Checks feature flag

Q1: What design principle does Factory follow?

Open Closed Principle

Dependency Inversion

Encapsulation
 */