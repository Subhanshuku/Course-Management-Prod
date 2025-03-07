package com.project.Student_Service.controller;


import com.project.Student_Service.DTO.CourseDTO;
import com.project.Student_Service.DTO.StudentDTO;
import com.project.Student_Service.entity.Student;
import com.project.Student_Service.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    // Inject the StudentServiceImpl instance using Spring's @Autowired annotation
    @Autowired
    StudentService studentService;

    /**
     * Add a new student.
     *
     * @param student The Student object to be added.
     * @return The added Student object.
     */
    @PostMapping("/add") //http://localhost:8050/student/add
    public Student addStudent(@Valid @RequestBody Student student){
        return studentService.addStudent(student);
    }

    /**
     * Retrieve all students.
     *
     * @return A list of all Student objects.
     */
    @GetMapping("/getAll")  //http://localhost:8050/student/getAll
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    /**
     * Retrieve a student by their ID.
     *
     * @param studentId The ID of the student to be retrieved.
     * @return The Student object corresponding to the given ID.
     */
    @GetMapping("/getById/{studentId}")  //http://localhost:8050/student/getById/1
    public Student getStudentById(@PathVariable("studentId") long studentId){
        return studentService.getStudentById(studentId);
    }

    /**
     * Delete a student by their ID.
     *
     * @param studentId The ID of the student to be deleted.
     * @return A message indicating the result of the deletion.
     */
    @DeleteMapping("/deleteById/{studentId}")  //http://localhost:8050/student/deleteById/1
    public String deleteStudentById(@PathVariable("studentId") long studentId){
        return studentService.deleteStudentById(studentId);
    }


    /**
     * Enroll a student into a course.
     *
     * @param studentId The ID of the student to be enrolled.
     * @param courseId The ID of the course the student is to be enrolled in.
     * @return The updated Student object after enrollment.
     */
    @PostMapping("enroll/{studentId}/{courseId}")  //http://localhost:8050/student/enroll/1/2
    public Student enrollIntoCourse(@PathVariable("studentId") long studentId, @PathVariable("courseId") long courseId){
        return studentService.enrollIntoCourse(studentId,courseId);
    }

    /**
     * Drop a student from a course.
     *
     * @param studentId The ID of the student to be dropped.
     * @param courseId The ID of the course the student is to be dropped from.
     * @return The updated Student object after dropping the course.
     */
    @PostMapping("/drop/{studentId}/{courseId}")  //http://localhost:8050/student/drop/1/2
    public Student dropStudentFromCourse(@PathVariable("studentId") long studentId,@PathVariable("courseId") long courseId){
        return studentService.dropStudentFromCourse(studentId,courseId);
    }

    /**
     * Retrieve all students by course ID.
     * Feign method which will be used by the Course Service
     * @param courseId The ID of the course.
     * @return A list of Student objects enrolled in the course.
     */
    @GetMapping("/getAllStudentsByCourseId/{courseId}") //http://localhost:8050/student/getAllStudentsByCourseId/1
    public List<Student> getAllStudentsByCourseId(@PathVariable("courseId") long courseId) {
        return studentService.getAllStudentsByCourseId(courseId);
    }

    @PutMapping("/update/{studentId}")
    public String updateStudent(@PathVariable long studentId,@RequestBody Student student){
        return studentService.updateStudent(studentId,student);
    }

    @GetMapping("/getCourseByStudentId/{studentId}")
    public CourseDTO getCourseByStudentId(@PathVariable long studentId){
        return studentService.getCourseByStudentId(studentId);
    }

}
