package com.pgh.spring_data_jpa0414.controller;

import com.pgh.spring_data_jpa0414.entity.User;
import com.pgh.spring_data_jpa0414.service.ExcelExport2007;
import com.pgh.spring_data_jpa0414.service.IUserService;
import com.pgh.spring_data_jpa0414.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *
 * @author kevin
 * @version 1.0
 * @date 2022/1/11
 */
@RestController
public class UserController {

    @Autowired
    IUserService iUserService;

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") Long id){

        User user = iUserService.findById(id);
        return Result.ok(user);
    }







    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public Result save(@RequestBody User user){
//        user.setCreateDt(Instant.now());
//        user.setCreateDt2(LocalDateTime.now());
        User save = iUserService.save(user);
        return Result.ok(save);
    }

    @RequestMapping(value = "/user/excle",method = RequestMethod.GET)
    public Result excle(HttpServletRequest request, HttpServletResponse response){
//        new Thread(() -> {
            ExcelExport2007.getExcelExport(response);
//        }).start();
        System.out.println("end");
        return Result.ok("Success");
    }
}
