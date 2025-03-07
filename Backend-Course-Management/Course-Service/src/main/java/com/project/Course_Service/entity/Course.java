package com.project.Course_Service.entity;

import com.project.Course_Service.DTO.StudentDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @Min(value = 1, message = "courseId cannot be blank")
    private long courseId;

    @NotBlank(message="Course Name Can't be Blank")
    private String courseName;

    @NotBlank(message = "Course Description can't be Blank")
    private String courseDesc;


    transient private List<StudentDTO> students=new ArrayList<>();

}
