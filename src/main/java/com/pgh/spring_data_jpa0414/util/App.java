package com.pgh.spring_data_jpa0414.util;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/4/20
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();


        for (int i = 0; i < 100; i++) {

            Object forObject = restTemplate.getForObject("https://juejin.cn/post/7088973471505448967", String.class);
            System.out.println(forObject);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}


