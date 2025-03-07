package com.project.Admin_Service.service;

import com.project.Admin_Service.DTO.CourseDTO;
import com.project.Admin_Service.DTO.StudentDTO;
import com.project.Admin_Service.externalService.CourseService;
import com.project.Admin_Service.externalService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    CourseService courseService;

    @Autowired
    StudentService studentService;


    @Override
    public String enrollStudent(long studentId, long courseId) {
        StudentDTO student = studentService.getStudentById(studentId);

        CourseDTO course = courseService.getCourseById(courseId);

        student.setCourseId(courseId);
        course.getStudents().add(student);

        return "Student with id: "+studentId+" is registered in course with course id: "+courseId;
    }
}
