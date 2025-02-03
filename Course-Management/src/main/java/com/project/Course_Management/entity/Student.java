package com.project.Course_Management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    private int studentId;
    private String studentName;
    private String studentEmail;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;
}
