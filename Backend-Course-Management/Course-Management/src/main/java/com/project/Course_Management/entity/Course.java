package com.project.Course_Management.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private int courseId;
    private String courseName;
    private String courseDesc;


    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Student> students=new ArrayList<>();

    @OneToOne(mappedBy = "course",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Teacher teacher;
}
