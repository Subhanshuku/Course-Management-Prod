package com.project.Course_Management.controller;

import com.project.Course_Management.entity.Course;
import com.project.Course_Management.entity.Student;
import com.project.Course_Management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Course>> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") int id){
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("id") int id){
        return ResponseEntity.ok(courseService.deleteCourseById(id));
    }

    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<Course> enrollStudentInCourse(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId){
        return courseService.enrollStudentInCourse(studentId,courseId);
    }

    @DeleteMapping("/drop/{studentId}/{courseId}")
    public ResponseEntity<Course> dropStudentFromCourse(@PathVariable("studentId") int studentId,@PathVariable("courseId") int courseId){
        return courseService.dropStudentFromCourse(studentId,courseId);
    }
}
