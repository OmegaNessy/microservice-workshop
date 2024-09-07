package com.omeganessy.micro_sender.controller;

import com.omeganessy.micro_sender.entity.NotificationRequest;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.micrometer.common.util.StringUtils;
import io.micrometer.core.instrument.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/notification")
    public void sendNotification(@RequestBody NotificationRequest request) {
        logger.info("Received notification request: {}", request);

        rabbitTemplate.convertAndSend("exchange", "sender", request.getMessage());
        logger.info("Published message to RabbitMQ queue: {}", request.getMessage());
    }

}