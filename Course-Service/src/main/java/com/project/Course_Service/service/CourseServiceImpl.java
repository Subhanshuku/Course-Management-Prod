package com.project.Course_Service.service;

import com.project.Course_Service.DTO.CourseDTO;
import com.project.Course_Service.DTO.StudentDTO;
import com.project.Course_Service.entity.Course;
import com.project.Course_Service.exception.CourseAlreadyPresent;
import com.project.Course_Service.exception.NoCourseFoundByIdException;
import com.project.Course_Service.exception.NoCourseFoundException;
import com.project.Course_Service.externalServices.StudentServices;
import com.project.Course_Service.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    // Inject the CourseRepo instance using Spring's @Autowired annotation
    @Autowired
    CourseRepo courseRepo;

    // Inject the StudentServices instance, which is a Feign client, using Spring's @Autowired annotation
    @Autowired
    StudentServices studentServices;

    /**
     * Adds a new course to the repository.
     *
     * @param course the course to be added
     * @return CourseDTO with the details of the added course
     * @throws CourseAlreadyPresent if the course already exists
     */
    public CourseDTO addCourse(Course course) {
        long id=course.getCourseId();
        Course course2=courseRepo.findById(id).orElse(null);
        if(course2!=null){
            throw new CourseAlreadyPresent("Course with id " + id + " is already present");
        }
        Course course1 = courseRepo.save(course);
        CourseDTO dto=new CourseDTO(course1.getCourseId(),course1.getCourseName(), course.getCourseDesc());
        return dto;
    }

    /**
     * Retrieve all courses and convert them to CourseDTO objects.
     *
     * @return A list of CourseDTO objects representing all courses.
     * @throws NoCourseFoundException if there are no courses present.
     */
    public List<CourseDTO> getAllCourse(){
        List<Course> courses = courseRepo.findAll();

        if(courses.isEmpty()){
            throw new NoCourseFoundException("There is no course present");
        }

        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getCourseId(),course.getCourseName(),course.getCourseDesc());
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    /**
     * Retrieve a course by its ID.
     *
     * @param courseId The ID of the course to be retrieved.
     * @return The Course object corresponding to the given ID.
     * @throws NoCourseFoundByIdException if no course with the given ID is present.
     */
    public Course getCourseById(long courseId) {
        Course course = courseRepo.findById(courseId).orElse(null);

        if(course==null){
            throw new NoCourseFoundByIdException("No Course with id "+ courseId + " is present");
        }

        List<StudentDTO> listOfStudent=studentServices.getAllStudentsByCourseId(courseId);
        course.setStudents(listOfStudent);

        return course;
    }

    /**
     * Delete a course by its ID.
     *
     * @param courseId The ID of the course to be deleted.
     * @return A message indicating the result of the deletion.
     * @throws NoCourseFoundByIdException if no course with the given ID is present to delete.
     */
    public String deleteCourseById(long courseId) {
        Course course = courseRepo.findById(courseId).orElse(null);
        if(course==null){
            throw new NoCourseFoundByIdException("No Course with Id "+courseId+" is present to delete");
        }
        courseRepo.deleteById(courseId);
        return "Deleted";
    }

    @Override
    public void updateCourse(long courseId, Course course) {
        Course course1=courseRepo.findById(courseId).orElse(null);

        course1.setCourseName(course.getCourseName());
        course1.setCourseDesc(course.getCourseDesc());
        courseRepo.save(course1);
    }

    @Override
    public CourseDTO getCourseByIdForStudent(long courseId) {
        Course course = courseRepo.findById(courseId).orElse(null);

        if(course==null){
            throw new NoCourseFoundByIdException("No Course with id "+ courseId + " is present");
        }

        return new CourseDTO(course.getCourseId(),course.getCourseName(),course.getCourseDesc());
    }

    /**
     * Retrieve a course by its ID for teacher microservice.
     *
     * @param courseId The ID of the course to be retrieved.
     * @return The Course object corresponding to the given ID.
     * @throws NoCourseFoundByIdException if no course with the given ID is present.
     */
    public Course getCourseByIdForTeacher(long courseId){
        Course course = courseRepo.findById(courseId).orElse(null);

        if(course==null){
            throw new NoCourseFoundByIdException("No Course with id "+ courseId + " is present");
        }

        return course;
    }
}
