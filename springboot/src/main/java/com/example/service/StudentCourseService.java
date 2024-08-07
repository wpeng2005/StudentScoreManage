package com.example.service;

import com.example.entity.StudentCourse;
import com.example.exception.CustomException;
import com.example.mapper.StudentCourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentCourseService {
    @Resource
    private StudentCourseMapper studentCourseMapper;

    public void add(StudentCourse studentCourse){
        StudentCourse course = studentCourseMapper.selectByCondition(studentCourse);//通过学生ID和课程ID做一次查询的筛选，看看这个学生有没有选过这个课程
        if(course!=null){
            throw new CustomException("你已经选过这个课程");
        }
        studentCourseMapper.insert(studentCourse);
    }

    public PageInfo<StudentCourse> selectPage(Integer pageNum, Integer pageSize, StudentCourse studentCourse) {
        PageHelper.startPage(pageNum,pageSize);
        List<StudentCourse> list= studentCourseMapper.selectAll(studentCourse);
        return PageInfo.of(list);
    }

    public void deleteById(Integer id) {
        studentCourseMapper.deleteById(id);
    }
}
