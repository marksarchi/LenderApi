package com.sarchi.lenderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LenderApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LenderApiApplication.class, args);
    }

}
