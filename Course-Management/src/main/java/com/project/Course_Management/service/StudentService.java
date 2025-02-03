package com.project.Course_Management.service;

import com.project.Course_Management.entity.Course;
import com.project.Course_Management.entity.Student;
import com.project.Course_Management.repo.CourseRepo;
import com.project.Course_Management.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public ResponseEntity<Student> addStudent(Student student) {
        return new ResponseEntity<>(studentRepo.save(student), HttpStatus.OK);
    }


    public ResponseEntity<List<Student>> getAllStudent() {
        return new ResponseEntity<>(studentRepo.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<Student> getStudentById(int id) {
        Student student = studentRepo.findById(id).get();
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteStudentById(int id) {
        studentRepo.deleteById(id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }


    public ResponseEntity<Student> updateProfile(int id, Student student) {
        Student student1 = studentRepo.findById(id).get();

        student1.setStudentId(student.getStudentId());
        student1.setStudentName(student.getStudentName());
        student1.setStudentEmail(student.getStudentEmail());
        //student1.setCourse(student.getCourse());

        return new ResponseEntity<>(studentRepo.save(student),HttpStatus.OK);
    }

    public ResponseEntity<List<Course>> viewCoursesByStudentId(int id) {
        Student student = studentRepo.findById(id).get();

        return new ResponseEntity<>(List.of(student.getCourse()),HttpStatus.OK);
    }
}
