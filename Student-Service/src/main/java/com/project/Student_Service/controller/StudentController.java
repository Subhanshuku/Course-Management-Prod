package com.project.Student_Service.controller;


import com.project.Student_Service.entity.Student;
import com.project.Student_Service.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;


    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/getById/{studentId}")
    public Student getStudentById(@PathVariable("studentId") long studentId){
        return studentService.getStudentById(studentId);
    }

    @DeleteMapping("/deleteById/{studentId}")
    public String deleteStudentById(@PathVariable("studentId") long studentId){
        return studentService.deleteStudentById(studentId);
    }

    @PutMapping("/update/{studentId}")
    public Student updateStudentProfile(@PathVariable("studentId") long studentId,@RequestBody Student student){
        return studentService.updateProfile(studentId,student);
    }

    @PutMapping("enroll/{studentId}/{courseId}")
    public Student enrollIntoCourse(@PathVariable("studentId") long studentId,@PathVariable("courseId") long courseId){
        return studentService.enrollIntoCourse(studentId,courseId);
    }

    @DeleteMapping("/drop/{studentId}/{courseId}")
    public Student dropStudentFromCourse(@PathVariable("studentId") long studentId,@PathVariable("courseId") long courseId){
        return studentService.dropStudentFromCourse(studentId,courseId);
    }

    @GetMapping("/getAllStudentsByCourseId/{courseId}")
    public List<Student> getAllStudentsByCourseId(@PathVariable("courseId") long courseId) {
        return studentService.getAllStudentsByCourseId(courseId);
    }
}
