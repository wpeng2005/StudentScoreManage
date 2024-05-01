package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


public interface AdminMapper {
    @Select("select * from student.admin where username=#{username}")
    Admin selectByUsername(String username);
}


