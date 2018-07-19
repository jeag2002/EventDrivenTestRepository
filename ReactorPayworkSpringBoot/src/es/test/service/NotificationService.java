package es.test.service;

import es.test.doman.NotificationData;

public interface NotificationService {

    void initiateNotification(NotificationData notificationData) throws InterruptedException;

}