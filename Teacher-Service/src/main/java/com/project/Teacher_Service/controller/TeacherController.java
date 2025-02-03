package com.project.Teacher_Service.controller;

import com.project.Teacher_Service.DTO.CourseDTO;
import com.project.Teacher_Service.entity.Teacher;
import com.project.Teacher_Service.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherServiceImpl teacherService;

    @PostMapping("/add")
    public Teacher addTeacher(@RequestBody Teacher teacher){
        return teacherService.addStudent(teacher);
    }

    @GetMapping("/getAll")
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @GetMapping("/getById/{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") long teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    @DeleteMapping("/deleteById/{teacherId}")
    public  String deleteTeacherById(@PathVariable("teacherId") long teacherId){
        return teacherService.deleteTeacherById(teacherId);
    }

    @GetMapping("/getCourse/{teacherId}")
    public CourseDTO getTaughtCourseByTeacherId(@PathVariable("teacherId") long teacherId){
        return teacherService.getTaughtCourseByTeacherId(teacherId);
    }

    @PutMapping("/assignCourse/{teacherId}/{courseId}")
    public Teacher assignCourseToTeacher(@PathVariable("teacherId") long teacherId,@PathVariable("courseId") long courseId){
        return teacherService.assignCourseToTeacher(teacherId,courseId);
    }
}
