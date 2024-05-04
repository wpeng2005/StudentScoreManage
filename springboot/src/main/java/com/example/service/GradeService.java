package com.example.service;

import com.example.entity.Grade;
import com.example.mapper.GradeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GradeService {
    @Resource
    private GradeMapper gradeMapper;

    public PageInfo<Grade> selectPage(Integer pageNum, Integer pageSize, Grade grade) {
        PageHelper.startPage(pageNum,pageSize);
        List<Grade> list=gradeMapper.setlectAll(grade);
        return PageInfo.of(list);
    }

    public void add(Grade grade){
        gradeMapper.insert(grade);
    }
}
