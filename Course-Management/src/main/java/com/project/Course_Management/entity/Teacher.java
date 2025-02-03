package com.project.Course_Management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {
    @Id
    private int teacherId;
    private String teacherName;
    private String teacherEmail;

    @OneToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;
}
