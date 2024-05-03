package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {
    @Select("select * from student.student where username=#{username}")
    Student selectByUsername(String username);

    @Select("select * from student.student where name like concat('%',#{name},'%') and username like concat('%',#{username},'%')")
    List<Student> selectAll(Student student);

    @Insert("insert into student.student(username,password,name,phone,email,birth,avatar,sex,role) values " +
            "(#{username},#{password},#{name},#{phone},#{email},#{birth},#{avatar},#{sex},#{role})")
    void insert(Student student);

    @Delete("delete from  student.student where id=#{id}")
    void deleteByID(Integer id);

    @Update("update student.student set username=#{username},password=#{password}" +
            ",name=#{name},phone=#{phone},email=#{email},birth=#{birth}" +
            ",avatar=#{avatar},sex=#{sex},role=#{role} where id=#{id}")
    void updateById(Student student);

}
