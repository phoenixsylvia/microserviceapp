package com.phoenix.notification;

import com.phoenix.clients.fraud.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping()
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("New Notification...{} ", notificationRequest);
        notificationService.send(notificationRequest);

    }
}
