package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Account{
    private Integer id;
    private String  username;
    private String  password;
    private String  name;
    private String  phone;
    private String  email;
    private String  birth;
    private String  avatar;
    private String  sex;
    private String role;

}
