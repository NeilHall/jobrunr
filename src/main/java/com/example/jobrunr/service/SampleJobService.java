package com.example.jobrunr.service;

import org.springframework.stereotype.Service;

@Service
public class SampleJobService {
    public void doVeryLittle(String name) throws InterruptedException {
        System.out.println(("Hello " + name));
        Thread.sleep(5000);
    }
}
