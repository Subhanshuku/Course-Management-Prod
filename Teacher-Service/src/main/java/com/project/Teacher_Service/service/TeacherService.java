package com.project.Teacher_Service.service;

import com.project.Teacher_Service.DTO.CourseDTO;
import com.project.Teacher_Service.entity.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {

    public Teacher addStudent(Teacher teacher);

    public List<Teacher> getAllTeacher();

    public Teacher getTeacherById(long teacherId);

    public String deleteTeacherById(long teacherId);

    public CourseDTO getTaughtCourseByTeacherId(long teacherId);

    public Teacher assignCourseToTeacher(long teacherId, long courseId);
}
