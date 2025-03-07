package com.project.Admin_Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private long studentId;
    private String studentName;
    private String studentEmail;
    private long courseId;
}
