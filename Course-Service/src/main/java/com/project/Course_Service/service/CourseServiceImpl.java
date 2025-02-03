package com.project.Course_Service.service;



import com.project.Course_Service.DTO.StudentDTO;
import com.project.Course_Service.entity.Course;
import com.project.Course_Service.exception.NoCourseFoundByIdException;
import com.project.Course_Service.exception.NoCourseFoundException;
import com.project.Course_Service.exception.DeleteCourseByIdException;
import com.project.Course_Service.exception.SaveCourseException;
import com.project.Course_Service.externalServices.StudentServices;
import com.project.Course_Service.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    StudentServices studentServices;

    public Course addCourse(Course course) {
        try{
            return courseRepo.save(course);
        }catch (Exception e){
            throw new SaveCourseException("Course doesn't saved successfully");
        }
    }

    public List<Course> getAllCourse() throws NoCourseFoundException {
        List<Course> courses = courseRepo.findAll();
        if(courses.size()==0){
            throw new NoCourseFoundException("There is no course present");
        }
        return courses;
    }

    public Course getCourseById(long courseId) {
        Course course = courseRepo.findById(courseId).orElse(null);

        if(course==null){
            throw new NoCourseFoundByIdException("No Course with id "+ courseId + " is present");
        }

        List<StudentDTO> listOfStudent=studentServices.getAllStudentsByCourseId(courseId);
        course.setStudents(listOfStudent);

        return course;
    }

    public String deleteCourseById(long courseId) {
        Course course = courseRepo.findById(courseId).orElse(null);
        if(course==null){
            throw new NoCourseFoundByIdException("No Course with Id "+courseId+" is present to delete");
        }
        courseRepo.deleteById(courseId);
        return "Deleted";
    }

    public Course getCourseByIdForTeacher(long courseId){
        Course course = courseRepo.findById(courseId).orElse(null);

        if(course==null){
            throw new NoCourseFoundByIdException("No Course with id "+ courseId + " is present");
        }

        return course;
    }
}
