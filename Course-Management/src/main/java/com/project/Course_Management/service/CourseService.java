package com.project.Course_Management.service;

import com.project.Course_Management.entity.Course;
import com.project.Course_Management.entity.Student;
import com.project.Course_Management.repo.CourseRepo;
import com.project.Course_Management.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    StudentRepo studentRepo;

    public ResponseEntity<Course> addCourse(Course course) {
        Course course1 = courseRepo.save(course);
        return new ResponseEntity<>(course1, HttpStatus.OK);
    }

    public ResponseEntity<List<Course>> getAllCourse() {
        return new ResponseEntity<>(courseRepo.findAll(),HttpStatus.OK);
    }

    public Course getCourseById(int id) {
        return courseRepo.findById(id).orElse(null);

    }

    public String deleteCourseById(int id) {
        courseRepo.deleteById(id);
        return "Success";
    }

    public ResponseEntity<Course> enrollStudentInCourse(int studentId, int courseId) {
        Student student = studentRepo.findById(studentId).get();
        Course course = courseRepo.findById(courseId).get();

        student.setCourse(course);
        course.getStudents().add(student);
        studentRepo.save(student);
        return new ResponseEntity<>(courseRepo.save(course),HttpStatus.OK);
    }

    public ResponseEntity<Course> dropStudentFromCourse(int studentId, int courseId) {
        Student student = studentRepo.findById(studentId).get();
        Course course = courseRepo.findById(courseId).get();

        student.setCourse(null);
        course.getStudents().remove(student);
        studentRepo.save(student);
        return new ResponseEntity<>(courseRepo.save(course),HttpStatus.OK);
    }
}
