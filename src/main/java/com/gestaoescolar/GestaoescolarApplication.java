package com.gestaoescolar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GestaoescolarApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoescolarApplication.class, args);
    }

}
