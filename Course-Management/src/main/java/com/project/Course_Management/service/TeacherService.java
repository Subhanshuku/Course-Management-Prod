package com.project.Course_Management.service;

import com.project.Course_Management.entity.Course;
import com.project.Course_Management.entity.Teacher;
import com.project.Course_Management.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    public ResponseEntity<Teacher> addStudent(Teacher teacher) {
        Teacher teacher1 = teacherRepo.save(teacher);
        return new ResponseEntity<>(teacher1, HttpStatus.OK);
    }

    public ResponseEntity<List<Teacher>> getAllTeacher() {
        return new ResponseEntity<>(teacherRepo.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<Teacher> getTeacherById(int id) {
        Teacher teacher=teacherRepo.findById(id).get();
        return new ResponseEntity<>(teacher,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTeacherById(int id) {
        teacherRepo.deleteById(id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    public ResponseEntity<Course> getTaughtCourseByTeacherId(int id) {
        Teacher teacher=teacherRepo.findById(id).get();
        return new ResponseEntity<>(teacher.getCourse(),HttpStatus.OK);
    }
}
