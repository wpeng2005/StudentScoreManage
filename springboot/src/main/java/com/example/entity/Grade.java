package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private double score;
    private String comment;
    private String feedback;
    private String studentName;
    private String courseName;

}
