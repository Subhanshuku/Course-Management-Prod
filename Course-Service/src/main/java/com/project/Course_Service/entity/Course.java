package com.project.Course_Service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.Course_Service.DTO.StudentDTO;
import jakarta.persistence.*;
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
    private long courseId;
    private String courseName;
    private String courseDesc;

    transient private List<StudentDTO> students=new ArrayList<>();

}
