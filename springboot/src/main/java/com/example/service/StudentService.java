package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.RoleEnum;
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
    public void adds(Student student){
        studentMapper.insert(student);
    }

    public void deleteById(Integer id){
        studentMapper.deleteByID(id);
    }
    public void updateById(Student student){
        studentMapper.updateById(student);
    }

    //注册功能
    public void register(Account account) {
        Student student = new Student();
        student.setUsername(account.getUsername());  //账号
        student.setPassword(account.getPassword());  //属性
        this.add(student);//调用下方的add
    }
    private void add(Student student){
        Student dbstudent = studentMapper.selectByUsername(student.getUsername());
        if(dbstudent!=null){//已经有同名账号
            throw new CustomException("账号已存在");
        }
        if(ObjectUtil.isEmpty(student.getName())){
            student.setName(student.getUsername());
        }
        student.setRole(RoleEnum.STUDENT.name());
        studentMapper.insert(student);
    }
}
