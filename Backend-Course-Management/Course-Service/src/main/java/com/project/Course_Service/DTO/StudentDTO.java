package com.project.Course_Service.DTO;

import com.project.Course_Service.entity.Course;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private int studentId;
    private String studentName;
    private String studentEmail;
    private int courseId;


}
