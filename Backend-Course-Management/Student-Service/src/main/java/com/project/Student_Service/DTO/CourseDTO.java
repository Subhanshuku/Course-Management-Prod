package com.project.Student_Service.DTO;

import com.project.Student_Service.entity.Student;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private long courseId;
    private String courseName;
    private String courseDesc;

    private List<Student> students=new ArrayList<>();
}
