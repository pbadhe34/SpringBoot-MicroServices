package com.stream.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.stream.stream.GreetingsStreams;

@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {
}
