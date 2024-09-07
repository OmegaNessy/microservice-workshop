package com.omeganessy.micro_recipient.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private RabbitTemplate rabbitTemplate;
    private final MeterRegistry meterRegistry;

    private final Deque<String> messages = new ArrayDeque<>();

    @GetMapping("/message")
    public String getMessages() {
        Counter counter = Counter.builder("api_messages_get")
                .tag("messages", messages.toString())
                .description("a number of requests to /message endpoint")
                .register(meterRegistry);
        counter.increment();

        logger.info("Retrieving message from in-memory storage.");

        return messages.isEmpty() ? "" : messages.pollFirst();
    }

    @PostMapping("message-test")
    public void sendTestMessage(@RequestParam String message){
        messages.addLast(message);
    }

    @Scheduled(fixedRate = 5000)
    public void receiveMessages() {
        Object message = rabbitTemplate.receiveAndConvert("notification");
        logger.info("received from rabbitmq: {}", message);
        if (message != null) {
            messages.addLast(message.toString());
            logger.info("Received message: {}", message);
        }
    }
}