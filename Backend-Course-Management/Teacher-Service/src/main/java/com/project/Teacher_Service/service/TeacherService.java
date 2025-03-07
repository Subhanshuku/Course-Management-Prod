package com.project.Teacher_Service.service;

import com.project.Teacher_Service.DTO.CourseDTO;
import com.project.Teacher_Service.DTO.TeacherDTO;
import com.project.Teacher_Service.entity.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {

    public TeacherDTO addStudent(Teacher teacher);

    public List<Teacher> getAllTeacher();

    public Teacher getTeacherById(long teacherId);

    public String deleteTeacherById(long teacherId);

    public CourseDTO getTaughtCourseByTeacherId(long teacherId);

    public Teacher assignCourseToTeacher(long teacherId, long courseId);

    String updateTeacher(long teacherId, Teacher teacher);

    Teacher dropTeacherFromCourse(long teacherId, long courseId);
}
