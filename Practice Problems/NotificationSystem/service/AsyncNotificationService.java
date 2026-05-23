package NotificationSystem.service;

import NotificationSystem.Model.Notification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncNotificationService {
        private final NotificationDispatcher dispatcher;
        private final ExecutorService executorService;


        public AsyncNotificationService(NotificationDispatcher dispatcher, ExecutorService executorService) {
            this.dispatcher = dispatcher;
            this.executorService = Executors.newFixedThreadPool(10);
        }

        public void sendNotification( Notification notification){
            executorService.submit(() -> dispatcher.dispatch(notification));
        }
    }