package com.project.Course_Service.controller;

import com.project.Course_Service.DTO.CourseDTO;
import com.project.Course_Service.entity.Course;
import com.project.Course_Service.service.CourseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    // Inject the CourseServiceImpl instance using Spring's @Autowired annotation
    @Autowired
    CourseServiceImpl courseService;

    /**
     * Handles HTTP POST requests for adding a new course.
     *
     * @param course the Course object to be added
     * @return CourseDTO with the details of the added course
     */
    @PostMapping("/add")  //http://localhost:8055/course/add
    public CourseDTO addCourse(@RequestBody @Valid Course course ){
        return courseService.addCourse(course);
    }

    /**
     * Retrieve all courses.
     * Made courseDTO because there was no need to return the list of students along with courses
     * @return A list of all Course objects.
     */
    @GetMapping("/getAll")  //http://localhost:8055/course/getAll
    public List<CourseDTO> getAllCourse() {
        return courseService.getAllCourse();
    }

    /**
     * Retrieve a course by its ID.
     *
     * @param courseId The ID of the course to be retrieved.
     * @return The Course object corresponding to the given ID.
     */
    @GetMapping("/getById/{courseId}")  //http://localhost:8055/course/getById/1
    public Course getCourseById(@PathVariable("courseId") long courseId)   {
        return courseService.getCourseById(courseId);
    }

    /**
     * Retrieve a course by its ID for teachers.
     * Above controller do the same but this controller is formed for Teacher-Microservice
     * Above microservice cannot be used as there is a calling form student-microservice
     * @param courseId The ID of the course to be retrieved for a teacher.
     * @return The Course object corresponding to the given ID for a teacher.
     */
    @GetMapping("/getByIdForTeacher/{courseId}")  //http://localhost:8055/course/getByIdForTeacher/1
    public Course getCourseByIdForTeacher(@PathVariable("courseId") long courseId)   {
        return courseService.getCourseByIdForTeacher(courseId);
    }

    @GetMapping("/getByIdForStudent/{courseId}")
    public CourseDTO getCourseByIdForStudent(@PathVariable long courseId){
        return courseService.getCourseByIdForStudent(courseId);
    }

    /**
     * Delete a course by its ID.
     *
     * @param courseId The ID of the course to be deleted.
     * @return A message indicating the result of the deletion.
     */
    @DeleteMapping("/delete/{courseId}") //getByIdForTeacher/delete/1
    public String deleteCourseById(@PathVariable("courseId") long courseId){
        return courseService.deleteCourseById(courseId);
    }

    @PutMapping("/update/{courseId}")
    public void updateCourse(@PathVariable long courseId,@RequestBody Course course){
        courseService.updateCourse(courseId,course);
    }
}
