package com.example.haibazoshop;

import com.example.haibazoshop.configuration.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@Import(CorsConfig.class)
@SpringBootApplication
public class HaibazoshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaibazoshopApplication.class, args);
	}

}
