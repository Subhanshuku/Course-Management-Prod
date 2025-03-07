package com.project.Teacher_Service.controller;

import com.project.Teacher_Service.DTO.CourseDTO;
import com.project.Teacher_Service.DTO.TeacherDTO;
import com.project.Teacher_Service.entity.Teacher;
import com.project.Teacher_Service.service.TeacherServiceImpl;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    // Inject the TeacherServiceImpl instance using Spring's @Autowired annotation
    @Autowired
    TeacherServiceImpl teacherService;

    /**
     * Add a new teacher.
     *
     * @param teacher The Teacher object to be added.
     * @return The added Teacher object.
     */
    @PostMapping("/add") //http://localhost:8060/teacher/add
    public TeacherDTO addTeacher(@Valid @RequestBody Teacher teacher){
        return teacherService.addStudent(teacher);
    }

    /**
     * Retrieve all teachers.
     *
     * @return A list of all Teacher objects.
     */
    @GetMapping("/getAll") //http://localhost:8060/teacher/getAll
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeacher();
    }

    /**
     * Retrieve a teacher by their ID.
     *
     * @param teacherId The ID of the teacher to be retrieved.
     * @return The Teacher object corresponding to the given ID.
     */
    @GetMapping("/getById/{teacherId}") //http://localhost:8060/teacher/getById/1
    public Teacher getTeacherById(@PathVariable("teacherId") long teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    /**
     * Delete a teacher by their ID.
     *
     * @param teacherId The ID of the teacher to be deleted.
     * @return A message indicating the result of the deletion.
     */
    @DeleteMapping("/deleteById/{teacherId}") //http://localhost:8060/teacher/deleteById/1
    public  String deleteTeacherById(@PathVariable("teacherId") long teacherId){
        return teacherService.deleteTeacherById(teacherId);
    }

    /**
     * Retrieve the course taught by a teacher based on their ID.
     *
     * @param teacherId The ID of the teacher.
     * @return The CourseDTO object representing the course taught by the teacher.
     */
    @GetMapping("/getCourse/{teacherId}")  //http://localhost:8060/teacher/getCourse/1
    public CourseDTO getTaughtCourseByTeacherId(@PathVariable("teacherId") long teacherId){
        return teacherService.getTaughtCourseByTeacherId(teacherId);
    }

    /**
     * Assign a course to a teacher.
     *
     * @param teacherId The ID of the teacher.
     * @param courseId The ID of the course to be assigned.
     * @return The updated Teacher object after assignment.
     */
    @PostMapping("/assignCourse/{teacherId}/{courseId}")  //http://localhost:8060/teacher/assignCourse
    public Teacher assignCourseToTeacher(@PathVariable("teacherId") long teacherId,@PathVariable("courseId") long courseId){
        return teacherService.assignCourseToTeacher(teacherId,courseId);
    }

    @PostMapping("/dropCourse/{teacherId}/{courseId}")
    public Teacher dropCourse(@PathVariable long teacherId,@PathVariable long courseId){
        return teacherService.dropTeacherFromCourse(teacherId,courseId);
    }

    @PutMapping("/update/{teacherId}")
    public String updateTeacher(@PathVariable long teacherId,@RequestBody Teacher teacher){
        return teacherService.updateTeacher(teacherId,teacher);
    }

}