package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select("select * from student.studnt where username=#{username}")
    Student selectByUsername(String username);

}
