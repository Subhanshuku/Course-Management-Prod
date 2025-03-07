package com.project.Student_Service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOForStudent {
    private long courseId;
    private String courseName;
    private String courseDesc;
}
