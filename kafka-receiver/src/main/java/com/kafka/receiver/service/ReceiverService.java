package com.kafka.receiver.service;

import com.kafka.receiver.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Service
@Slf4j
public class ReceiverService {

    @Bean
    public Consumer<Car> carReceiver() {
        return car -> {
            log.info("Received: {}", car);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

    }
}