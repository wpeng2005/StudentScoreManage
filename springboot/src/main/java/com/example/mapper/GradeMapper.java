package com.example.mapper;

import com.example.entity.Grade;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

public interface GradeMapper {
    @Insert("insert into student.grade(student_id,course_id,score,comment,feedback) values " +
            "(#{studentId},#{courseId},#{score},#{comment},#{feedback})")
    void insert(Grade grade);

    List<Grade> selectAll(Grade grade);//需要关联查询
    @Update("update student.grade set score=#{score},comment=#{comment},feedback=#{feedback} where id=#{id}")
    void updateById(Grade grade);

    @Select("select * from student.grade where student_id=#{studentId} and course_id=#{courseId}")
    Grade selectByCondition(Grade grade);

    @Delete("delete  from student.grade where id=#{id}")
    void deleteById(Integer id);
}
