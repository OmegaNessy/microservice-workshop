package com.omeganessy.micro_collector.boundaries;

import com.omeganessy.micro_collector.entity.Message;
import com.omeganessy.micro_collector.repository.JpaMessageRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@AllArgsConstructor
public class MessageCollector {

    private static final Logger logger = LoggerFactory.getLogger(MessageCollector.class);

    private RestTemplate restTemplate;
    private JpaMessageRepository repository;


    @Scheduled(fixedRate = 10000) // Run every 10 seconds (M = 10)
    public void collectMessages() {
        logger.info("Collecting messages from micro-recipient...");

        String response = restTemplate.getForObject("http://recipient.default.svc.cluster.local:8081/message", String.class);
//        String response = restTemplate.getForObject("http://localhost:8081/message", String.class);
        logger.info("Received message: {}", response);
        if(response!=null && !"null".equals(response)){
            Message message = new Message();
            message.setId(UUID.randomUUID());
            message.setMessage(response);
            logger.info("Saving entity: {}", message.getMessage());
            repository.save(message);
        }

        logger.info("Received message: {}", response);
    }
}