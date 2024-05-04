package com.example.mapper;

import com.example.entity.Grade;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface GradeMapper {
    @Insert("insert into student.grade(student_id,course_id,score,comment,feedback) values " +
            "(#{studentId},#{courseId},#{score},#{comment},#{feedback})")
    void insert(Grade grade);

    List<Grade> setlectAll(Grade grade);//需要关联查询
}
