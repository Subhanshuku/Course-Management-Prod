package com.project.Admin_Service.DTO;

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
    private List<StudentDTO> students = new ArrayList<>();
}
