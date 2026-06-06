package PubSubApplication.core;

import PubSubApplication.model.Message;

public interface Subscriber {
    void onMessage(Message message);
}
