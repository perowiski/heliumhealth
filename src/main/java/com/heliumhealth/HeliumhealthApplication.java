package com.heliumhealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HeliumhealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeliumhealthApplication.class, args);
    }

}
