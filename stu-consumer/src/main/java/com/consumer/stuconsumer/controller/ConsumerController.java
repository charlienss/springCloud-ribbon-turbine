package com.consumer.stuconsumer.controller;

import com.consumer.stuconsumer.entity.Student;
import com.consumer.stuconsumer.feign.ConsumerFeign;
import com.consumer.stuconsumer.feign.TestFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private ConsumerFeign consumerFeign;
//    @Resource
//    private TestFeign testFeign;

    @RequestMapping("/getAll/{id}")
    public Student getAll(@PathVariable("id") Integer id) {
        Student stu = consumerFeign.getAll(id);
        return stu;
    }


}
