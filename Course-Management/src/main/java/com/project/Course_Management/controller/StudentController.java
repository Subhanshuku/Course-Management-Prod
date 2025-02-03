package com.project.Course_Management.controller;

import com.project.Course_Management.entity.Course;
import com.project.Course_Management.entity.Student;
import com.project.Course_Management.repo.StudentRepo;
import com.project.Course_Management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/allStudent")
    public ResponseEntity<List<Student>> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id){
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") int id){
        return studentService.deleteStudentById(id);
    }

    @PutMapping("/updateProfile/{id}")
    public ResponseEntity<Student> updateStudentProfile(@PathVariable("id") int id,@RequestBody Student student){
        return studentService.updateProfile(id,student);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<List<Course>> viewCoursesByStudentId(@PathVariable("id") int id){
        return studentService.viewCoursesByStudentId(id);
    }
}
