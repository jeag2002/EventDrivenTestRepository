package es.test.service.impl;

import org.springframework.stereotype.Service;

import es.test.doman.NotificationData;
import es.test.service.NotificationService;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void initiateNotification(NotificationData notificationData) throws InterruptedException {

        System.out.println("Notification service started for Notification ID: " + notificationData.getId());

        Thread.sleep(5000);

        System.out.println("Notification service ended for Notification ID: " + notificationData.getId());
    }

}