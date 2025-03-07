package com.project.Teacher_Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private long teacherId;
    private String teacherName;
    private String teacherEmail;
}
