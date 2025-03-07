package com.project.Student_Service.service;

import com.project.Student_Service.DTO.CourseDTO;
import com.project.Student_Service.DTO.StudentDTO;
import com.project.Student_Service.entity.Student;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StudentService {

    public Student addStudent(Student student);

    public List<Student> getAllStudent();

    public Student getStudentById(long studentId);

    public String deleteStudentById(long studentId);

    public Student enrollIntoCourse(long studentId, long courseId);

    public Student dropStudentFromCourse(long studentId, long courseId);

    public List<Student> getAllStudentsByCourseId(long courseId);


    String updateStudent(long studentId,Student student);

    CourseDTO getCourseByStudentId(long studentId);
}
