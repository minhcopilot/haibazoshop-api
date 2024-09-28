package com.example.haibazoshop.configuration;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class EnvConfig {

    public EnvConfig() {
        Dotenv dotenv = Dotenv.configure().filename(".env").load();
        dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
    }
}
