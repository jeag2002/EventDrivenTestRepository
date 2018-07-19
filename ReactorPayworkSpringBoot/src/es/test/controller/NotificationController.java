package es.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.test.doman.NotificationData;
import reactor.bus.Event;
import reactor.bus.EventBus;

@Controller
public class NotificationController {

    @Autowired
    private EventBus eventBus;

    @GetMapping("/startNotification/{param}")
    public ResponseEntity<NotificationData> startNotification(@PathVariable Integer param) {

        for (int i = 0; i < param; i++) {
            NotificationData data = new NotificationData();
            data.setId(i);
            eventBus.notify("notificationConsumer", Event.wrap(data));
            System.out.println("Notification " + i + ": notification task submitted successfully");
        }
        
        NotificationData data = new NotificationData();
        return new ResponseEntity<NotificationData>(data, HttpStatus.OK);
        

    }

}