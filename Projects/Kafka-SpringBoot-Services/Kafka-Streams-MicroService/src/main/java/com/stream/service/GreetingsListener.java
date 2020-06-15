package com.stream.service;

import com.stream.model.Greetings;
import com.stream.stream.GreetingsStreams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreetingsListener {
    @StreamListener(GreetingsStreams.INPUT)
    public void handleGreetings(@Payload Greetings greetings) {
    	//To highliht in RED
        log.error("Received greetings as : {}", greetings);
    }
}
