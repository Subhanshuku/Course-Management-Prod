package com.project.Student_Service.service;

import com.project.Student_Service.DTO.CourseDTO;
import com.project.Student_Service.entity.Student;
import com.project.Student_Service.exception.*;
import com.project.Student_Service.externalServices.CourseServices;
import com.project.Student_Service.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    CourseServices courseServices;

    public Student addStudent(Student student) {
        try{
            return studentRepo.save(student);
        }catch (Exception e){
            throw new SaveStudentException("Student does not saved");
        }
    }

    public List<Student> getAllStudent() {
        List<Student> students= studentRepo.findAll();
        if(students==null){
            throw new StudentNotFoundException("Student Not Found");
        }
        return students;
    }

    public Student getStudentById(long studentId) {
        Student student = studentRepo.findById(studentId).orElse(null);
        if(student==null){
            throw new StudentNotFoundByIdException("Student By Id "+studentId+" is not Found");
        }
        return student;
    }

    public String deleteStudentById(long studentId) {
        Student student=studentRepo.findById(studentId).orElse(null);
        if(student == null){
            throw new StudentNotFoundByIdException("Student By Id "+studentId+" is not Found to delete");
        }
        studentRepo.deleteById(studentId);
        return "Student Deleted Successfully";
    }


    public Student updateProfile(long studentId, Student student) {
        Student student1 = studentRepo.findById(studentId).orElse(null);

        if(student1==null){
            throw new StudentNotFoundByIdException("Student Not  Found with Id "+studentId+", Update Unsuccessfull");
        }

        return studentRepo.save(student);
    }

    public Student enrollIntoCourse(long studentId, long courseId) {
        Student student=studentRepo.findById(studentId).orElse(null);
        if(student == null){
            throw new StudentNotFoundByIdException("Student Not found with Student Id "+studentId+", Enrollment Unsuccessfull");
        }

        CourseDTO courseDTO=courseServices.getCourseById(courseId);

        student.setCourseId(courseId);
        courseDTO.getStudents().add(student);

        return studentRepo.save(student);
    }

    public Student dropStudentFromCourse(long studentId, long courseId) {
        Student student = studentRepo.findById(studentId).orElse(null);
        if(student == null){
            throw new StudentNotFoundByIdException("Student Not found with Student Id "+studentId+", Unregister from Course Unsuccessfull");
        }

        CourseDTO courseDTO = courseServices.getCourseById(courseId);
        if(courseDTO == null){
            throw new CourseNotFoundException("Course Not found with Course Id "+courseId+" , Unregister from Course Unsuccessfull");
        }

        student.setCourseId(0);
        courseDTO.getStudents().remove(student);
        return studentRepo.save(student);
    }

    public List<Student> getAllStudentsByCourseId(long courseId) {
        return studentRepo.findByCourseId(courseId);
    }

}