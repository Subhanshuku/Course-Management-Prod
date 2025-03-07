package com.project.Student_Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private long studentId;
    private String studentName;
    private String studentEmail;
    private List<CourseDTO> courses;
}
