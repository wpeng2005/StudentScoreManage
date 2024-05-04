package com.example.controller;

import com.example.common.Result;
import com.example.entity.Course;
import com.example.entity.StudentCourse;
import com.example.service.StudentCourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/StudentCourse")
public class StudentCourseController {
    @Resource
    private StudentCourseService studentCourseService;
    //分页查询
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                            StudentCourse studentCourse){
        PageInfo<StudentCourse> pageInfo = studentCourseService.selectPage(pageNum,pageSize,studentCourse);
        return Result.success(pageInfo);
    }

    @PostMapping("/add")
    public Result add(@RequestBody StudentCourse studentCourse){
        //把关联的数据插入到studentcourse这张表里面
        studentCourseService.add(studentCourse);
        return Result.success();
    }
}
