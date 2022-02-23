package com.pgh.spring_data_jpa0414.controller;


import com.pgh.spring_data_jpa0414.entity.SStudent;
import com.pgh.spring_data_jpa0414.repository.SSteudentRepository;
import com.pgh.spring_data_jpa0414.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pgh
 * @data 2021/4/14 11:33
 */
@RestController
//@RequestMapping(value = "/student")
public class SStudentcontroller {
    @Autowired
    private SSteudentRepository sSteudentRepository;


    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public Result findall(){
        Result result=new Result();

         List<SStudent> all = sSteudentRepository.findAll();
         result.setResult(all);
       return result;

    }
@RequestMapping(value = "test",method = RequestMethod.GET)
public void test(){

    System.out.println("测试成功");
}
@RequestMapping(value = "findbyId",method = RequestMethod.GET)
    public Result findById( String sname,int sid){
        Result result=new Result();
        result.setResult( sSteudentRepository.findbyid(sname,sid));
        return result;
}

}
