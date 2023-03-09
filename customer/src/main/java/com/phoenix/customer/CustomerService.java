package com.phoenix.customer;

import com.phoenix.clients.fraud.FraudCheckResponse;
import com.phoenix.clients.fraud.FraudClient;
import com.phoenix.clients.fraud.NotificationClient;
import com.phoenix.clients.fraud.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check if email is valid
        //todo: check if email is not taken

        customerRepository.saveAndFlush(customer);

        //check if fraudster using Rest Template
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//               // "http://localhost:8081/api/v1/fraud-check/{customerId}",
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//
//        );

        //Check if fraudster
       FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
        //todo: make it async, i.e add to queue
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, Welcome to Phoenix's app...", customer.getFirstName())

                )
        );


    }
}
