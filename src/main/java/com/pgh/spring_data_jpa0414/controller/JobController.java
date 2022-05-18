package com.pgh.spring_data_jpa0414.controller;

import com.pgh.spring_data_jpa0414.service.SampleJobService;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/2/24
 */
@RestController
public class JobController {

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private SampleJobService sampleJobService;

    @GetMapping("/run-job")
    public String runJob(@RequestParam(value = "name", defaultValue = "Hello World") String name) {

        BackgroundJob.enqueue(() -> System.out.println("Hello, world!"));

        jobScheduler.enqueue(() -> sampleJobService.execute(name));
        return "Job is enqueued.";

    }

    @GetMapping("/schedule-job")
    public String scheduleJob(
            @RequestParam(value = "name", defaultValue = "Hello World") String name,
            @RequestParam(value = "when", defaultValue = "PT3H") String when) {
//        jobScheduler.schedule(Instant.now().plus(Duration.parse(when)), () -> sampleJobService.execute(name));
        String daily = Cron.daily();
        // 0 0 0 12-21 * ?
        // 48-55 * * * *
        jobScheduler.scheduleRecurrently("48-55 * * * *", () -> sampleJobService.execute(name));
        return "Job is scheduled.";
    }

}
