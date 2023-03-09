package com.phoenix.clients.fraud;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String message) {

}
