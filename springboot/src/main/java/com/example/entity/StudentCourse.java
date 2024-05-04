package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourse {
    private Integer id;
    private String name;
    private String no;
    private Integer studentId;
    private Integer courseId;


}
