package com.consumer.stuconsumer.controller;

import com.consumer.stuconsumer.entity.Student;
import com.consumer.stuconsumer.feign.UserFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private UserFeignClient userFeignClient;
//    @Resource
//    private TestFeign testFeign;

    @RequestMapping("/getAll/{id}")
    public Student getAll(@PathVariable("id") Integer id) {
        Student stu = userFeignClient.getAll(id);
        return stu;
    }


}
