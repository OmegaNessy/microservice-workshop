package com.omeganessy.micro_sender.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomMetrics {

    private final Counter messageCounter;
    private final Random random = new Random();

    @Autowired
    public CustomMetrics(MeterRegistry registry) {
        this.messageCounter = Counter.builder("custom.messages.sent")
                .description("Number of messages sent by the micro-sender")
                .register(registry);
    }

    public void incrementMessageCounter() {
        messageCounter.increment(random.nextInt(10) + 1);
    }
}
