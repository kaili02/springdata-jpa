package com.pgh.spring_data_jpa0414;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class SpringDataJpa0414Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(SpringDataJpa0414Application.class, args);
    }

//    @Bean
//    public JobScheduler initJobRunr(DataSource dataSource, JobActivator jobActivator) {
//        return JobRunr.configure()
//                .useJobActivator(jobActivator)
//                .useStorageProvider(SqlStorageProviderFactory
//                        .using(dataSource))
//                .useBackgroundJobServer()
//                .useDashboard()
//                .initialize();
//    }

}
