package com.home.service.impl;

import com.home.entity.Student;
import com.home.mapper.StudentMapper;
import com.home.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private  StudentMapper studentMapper;


    @Override
    public Student getAllStu(Integer id) {
        return studentMapper.getAllStu(id);
    }
}
