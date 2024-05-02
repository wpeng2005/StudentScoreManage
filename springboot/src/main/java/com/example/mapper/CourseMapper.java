package com.example.mapper;

import com.example.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CourseMapper {
    @Select("select * from student.course where name like concat('%',#{name},'%') and course.no like concat('%',#{no},'%')  " +
            " and course.teacher like concat('%',#{teacher},'%') order by id desc ")
    List<Course> selectAll(Course course);

    @Insert("insert into student.course(name,no,descr,times,teacher) values (#{name},#{no},#{descr},#{times},#{teacher})")
    void insert(Course course);

    @Update("update student.course set name=#{name},no=#{no},descr=#{descr},times=#{times},teacher=#{teacher} where id=#{id}")
    void updateById(Course course);
    @Delete("delete from student.course where id=#{id}")
    void deleteById(Integer id);
}
