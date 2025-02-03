package com.project.Course_Management.controller;

import com.project.Course_Management.entity.Course;
import com.project.Course_Management.entity.Teacher;
import com.project.Course_Management.repo.TeacherRepo;
import com.project.Course_Management.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping("/addTeacher")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        return teacherService.addStudent(teacher);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Teacher>> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    @GetMapping("/getById/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("teacherId") int teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    @DeleteMapping("/deleteById/{teacherId}")
    public  ResponseEntity<String> deleteTeacherById(@PathVariable("teacherId") int teacherId){
        return teacherService.deleteTeacherById(teacherId);
    }

    @GetMapping("/getCourse/{teacherId}")
    public ResponseEntity<Course> getTaughtCourseByTeacherId(@PathVariable("teacherId") int teacherId){
        return teacherService.getTaughtCourseByTeacherId(teacherId);
    }
}
