package com.omeganessy.micro_visualizer.controller;

import com.omeganessy.micro_visualizer.entity.Message;
import com.omeganessy.micro_visualizer.repository.repository.JpaMessageRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestMessageController {
    private static final Logger logger = LoggerFactory.getLogger(RestMessageController.class);
    private final MeterRegistry meterRegistry;
    private final JpaMessageRepository repository;

    @GetMapping("/saved-messages")
    public List<Message> getSavedMessages() {
        logger.info("Retrieving messages from postgres storage.");
        List<Message> savedMessageList = repository.findAll();
        logger.info(savedMessageList.toString());
        Counter counter = Counter.builder("api_saved_messages_get")
                .tag("saved_messages", savedMessageList.stream().map((Message::getMessage)).toList().toString())
                .description("a number of requests to /saved-messages endpoint")
                .register(meterRegistry);
        counter.increment();

        return savedMessageList;
    }
}
