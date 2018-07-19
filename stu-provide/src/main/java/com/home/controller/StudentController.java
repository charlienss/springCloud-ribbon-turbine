package com.home.controller;

import com.home.entity.Student;
import com.home.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class StudentController {
    @Autowired
    private StudentService  studentService;

    @GetMapping("/getAll/{id}")
    public Student getAll(@PathVariable("id")Integer id){
        System.out.println("stu-provide:localhost:5865==>消费者查询学生时间:"+new Date().toLocaleString());
    Student stu =  studentService.getAllStu(id);
    return stu;

    }
}
