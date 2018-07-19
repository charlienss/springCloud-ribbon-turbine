package com.home.mapper;

import com.home.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    Student getAllStu(Integer id);
}
