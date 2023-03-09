package com.phoenix.notification;

import com.phoenix.clients.fraud.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest){
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .sender("Sylvia Nanev")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()

        );
    }
}
