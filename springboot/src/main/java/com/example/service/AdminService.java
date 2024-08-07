package com.example.service;

import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {
    //登录功能
    @Resource
    private AdminMapper adminMapper;
    public Account login(Account account){
        Account dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if(dbAdmin==null){
            //没有用户
            throw new CustomException("账号或密码错误");
        }
        //比较密码
        if(!account.getPassword().equals(dbAdmin.getPassword())){
            throw new CustomException("账号或密码错误");
        }
        //两个条件都满足，说明登录成功
        return dbAdmin;
    }
}
