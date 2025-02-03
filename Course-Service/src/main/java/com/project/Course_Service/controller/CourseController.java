package com.project.Course_Service.controller;

import com.project.Course_Service.entity.Course;
import com.project.Course_Service.exception.NoCourseFoundByIdException;
import com.project.Course_Service.exception.NoCourseFoundException;
import com.project.Course_Service.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping("/getAll")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping("/getById/{courseId}")
    public Course getCourseById(@PathVariable("courseId") long courseId)   {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/getByIdForTeacher/{courseId}")
    public Course getCourseByIdForTeacher(@PathVariable("courseId") long courseId)   {
        return courseService.getCourseByIdForTeacher(courseId);
    }

    @DeleteMapping("/delete/{courseId}")
    public String deleteCourseById(@PathVariable("courseId") long courseId){
        return courseService.deleteCourseById(courseId);
    }
}
