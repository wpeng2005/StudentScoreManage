package com.example.service;

import com.example.entity.Account;
import com.example.entity.Course;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;
    public Account login(Account account){
        Account dbStudent = studentMapper.selectByUsername(account.getUsername());
        if(dbStudent==null){
            //没有用户
            throw new CustomException("账号或密码错误");
        }
        //比较密码
        if(!account.getPassword().equals(dbStudent.getPassword())){
            throw new CustomException("账号或密码错误");
        }
        //两个条件都满足，说明登录成功
        return dbStudent;
    }
    public List<Student> select(Student student){
        List<Student> studentList = studentMapper.selectAll(student);
        return studentList;
    }
    public void add(Student student){
        studentMapper.insert(student);
    }

    public void deleteById(Integer id){
        studentMapper.deleteByID(id);
    }
    public void updateById(Student student){
        studentMapper.updateById(student);
    }


}
