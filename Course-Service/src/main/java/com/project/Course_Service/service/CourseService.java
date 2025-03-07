package com.project.Course_Service.service;

import com.project.Course_Service.DTO.CourseDTO;
import com.project.Course_Service.entity.Course;
import com.project.Course_Service.exception.NoCourseFoundByIdException;
import com.project.Course_Service.exception.NoCourseFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {

    public CourseDTO addCourse(Course course);

    public List<CourseDTO> getAllCourse();

    public Course getCourseById(long courseId);

    public String deleteCourseById(long courseId);

    void updateCourse(long courseId, Course course);

    CourseDTO getCourseByIdForStudent(long courseId);
}
