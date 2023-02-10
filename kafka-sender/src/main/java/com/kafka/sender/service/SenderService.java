package com.kafka.sender.service;

import com.kafka.sender.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class SenderService {

    private final StreamBridge streamBridge;
    
    private final AtomicInteger counter = new AtomicInteger();

    public SenderService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Scheduled(fixedDelay = 100, initialDelay = 500)
    public void sendCar() {
        Integer id = counter.incrementAndGet();
        Car car = new Car(id, "car-" + id);
        log.info("sending {}", car);
        streamBridge.send("carSender", car);
    }
}
