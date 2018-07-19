package com.home;

import com.home.entity.Student;
import com.home.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@SpringBootApplication
@EnableDiscoveryClient
//@RestController
public class StuProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuProvideApplication.class, args);
    }

//    @Resource
//    private StudentMapper studentMapper;
//
//    @RequestMapping("one/{id}")
//    public Student one(@PathVariable("id") Integer id){
//
//      return   studentMapper.getAllStu(id);
//    }
//    @PutMapping
//    @DeleteMapping
//    @PutMapping
//    @GetMapping
}
