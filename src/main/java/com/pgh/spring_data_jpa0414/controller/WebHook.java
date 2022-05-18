package com.pgh.spring_data_jpa0414.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgh.spring_data_jpa0414.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/4/29
 */
@RestController
@Slf4j
public class WebHook {

    final String command = "D:\n" +
            "cd D:\\workspace\\gitee\\test2\n" +
            "git pull origin2 master\n" +
            "git push -f origin1 master";

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/webHook",method = RequestMethod.POST)
    public Result webHook(@RequestBody Object obj){
        log.info("obj: {}", obj);
        try {
            Runtime runtime = Runtime.getRuntime();
            Process exec = runtime.exec("E:\\source\\mypullpush.bat");
            InputStream fis = exec.getInputStream();
            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitVal = exec.waitFor();
            System.out.println("Process exitValue: " + exitVal);
            log.info("obj: {}", objectMapper.writeValueAsString(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok(obj);
    }
}
