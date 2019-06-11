package com.gestaoescolar.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@Slf4j
@Configuration
public class SchedulingImpl {


    @Scheduled(fixedDelay = 1000, initialDelay = 150)
    public LocalDate date() {
        LocalDate now = LocalDate.now();
        return now;
    }
}
