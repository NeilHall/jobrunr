package com.example.jobrunr.controller;

import com.example.jobrunr.service.SampleJobService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private final JobScheduler jobScheduler;
    private final SampleJobService sampleJobService;

    public SampleController(JobScheduler jobScheduler, SampleJobService sampleJobService) {
        this.jobScheduler = jobScheduler;
        this.sampleJobService = sampleJobService;
    }

    @GetMapping("/enqueue-one-job")
    public String enqueueOneJob(@RequestParam(name = "name", defaultValue = "world") String name) {
        jobScheduler.enqueue(() -> sampleJobService.doVeryLittle(name));
        return "one job enqueued";
    }
}
