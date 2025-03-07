package com.project.Student_Service.entity;

import com.project.Student_Service.DTO.CourseDTO;
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
public class Student {
    @Id
    @Min(value = 1, message = "studentId cannot be blank")
    private long studentId;

    @NotBlank(message = "Student Name cannot be Blank")
    private String studentName;

    @Email(message = "Provide a valid email")
    @NotBlank(message = "Email cannot be blank")
    private String studentEmail;

    private long courseId;
}
