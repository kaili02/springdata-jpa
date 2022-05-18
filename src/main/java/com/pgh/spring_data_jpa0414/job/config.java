package com.pgh.spring_data_jpa0414.job;

import com.pgh.spring_data_jpa0414.repository.UserDao;
import org.jobrunr.scheduling.JobPostProcessor;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/5/18
 */
@Component
public class config {

    @Bean
//    @ConditionalOnBean({JobScheduler.class})
    public JobPostProcessor jobPostProcessor(JobScheduler jobScheduler, UserDao userDao) {
        System.out.println("my job");
        return new JobPostProcessor(jobScheduler, userDao);
    }

}
