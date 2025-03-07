package com.project.Teacher_Service.entity;

import com.project.Teacher_Service.DTO.CourseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
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
public class Teacher {
    @Id
    @Min(value = 1, message = "teacherId cannot be blank")
    private long teacherId;

    @NotBlank(message = "Teacher Name cannot be empty")
    private String teacherName;

    @Email(message = "Enter a valid Email")
    @NotBlank(message = "Teacher email cannot be blank")
    private String teacherEmail;

    private long courseId;
//    transient List<CourseDTO> courses=new ArrayList<>();
}
