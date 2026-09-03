package NotificationSystem.factory;

import NotificationSystem.Model.ChannelType;
import NotificationSystem.channel.EmailNotificationChannel;
import NotificationSystem.channel.NotificationChannel;
import NotificationSystem.channel.PushNotificationChannel;
import NotificationSystem.channel.SmsNotificationChannel;

public class NotificationChannelFactory {

    //Factory with enum — avoids stringly-typed input, compile-time safety. Good.

    //never use string to create factory objct as user can send anything
    //doesnt violate open close principle as we can add new channel by just adding new class and modifying factory class
    // this class is used for creating ob not business logic
    public static NotificationChannel getChannel(ChannelType channelType){
        return switch (channelType){
            case EMAIL -> new EmailNotificationChannel();
            case SMS -> new SmsNotificationChannel();
            case PUSH -> new PushNotificationChannel();
        };
    }
}
//Factory
//   ↓
//Creates
//
//Email Strategy
//SMS Strategy
//Push Strategy
//
//NotificationChannel = Strategy Interface
//
//Email/SMS/Push = Concrete Strategies
//
//NotificationChannelFactory.getChannel() = Factory Method
//Factory Pattern
//      +
//Strategy Pattern
//
