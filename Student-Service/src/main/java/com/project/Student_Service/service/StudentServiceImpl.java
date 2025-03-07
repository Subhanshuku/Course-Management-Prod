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

    // Inject the StudentRepo instance using Spring's @Autowired annotation
    @Autowired
    StudentRepo studentRepo;

    // Inject the CourseServices instance, which is a Feign client, using Spring's @Autowired annotation
    @Autowired
    CourseServices courseServices;

    /**
     * Add a new student.
     *
     * @param student The Student object to be added.
     * @return The added Student object.
     */
    public Student addStudent(Student student) {
        long id=student.getStudentId();
        Student student1=studentRepo.findById(id).orElse(null);
        if(student1!=null){
            throw new StudentAlreadyPresent("Student already present with id "+id);
        }

        return studentRepo.save(student);

    }

    /**
     * Retrieve all students.
     *
     * @return A list of all Student objects.
     * @throws StudentNotFoundException if there are no students present.
     */
    public List<Student> getAllStudent() {
        List<Student> students= studentRepo.findAll();
        if(students.isEmpty()){
            throw new StudentNotFoundException("Student Not Found");
        }
        return students;
    }

    /**
     * Retrieve a student by their ID.
     *
     * @param studentId The ID of the student to be retrieved.
     * @return The Student object corresponding to the given ID.
     * @throws StudentNotFoundByIdException if no student with the given ID is found.
     */
    public Student getStudentById(long studentId) {
        Student student = studentRepo.findById(studentId).orElse(null);
        if(student==null){
            throw new StudentNotFoundByIdException("Student By Id "+studentId+" is not Found");
        }
        return student;
    }

    /**
     * Delete a student by their ID.
     *
     * @param studentId The ID of the student to be deleted.
     * @return A message indicating the result of the deletion.
     * @throws StudentNotFoundByIdException if no student with the given ID is found.
     */
    public String deleteStudentById(long studentId) {
        Student student=studentRepo.findById(studentId).orElse(null);
        if(student == null){
            throw new StudentNotFoundByIdException("Student By Id "+studentId+" is not Found to delete");
        }
        studentRepo.deleteById(studentId);
        return "Student Deleted Successfully";
    }

    /**
     * Enroll a student into a course.
     *
     * @param studentId The ID of the student to be enrolled.
     * @param courseId The ID of the course the student is to be enrolled in.
     * @return The updated Student object after enrollment.
     * @throws StudentNotFoundByIdException if no student with the given ID is found.
     */
    public Student enrollIntoCourse(long studentId, long courseId) {
        Student student=studentRepo.findById(studentId).orElse(null);
        if(student == null){
            throw new StudentNotFoundByIdException("Student Not found with Student Id "+studentId+", Enrollment Unsuccessfull");
        }

        CourseDTO courseDTO=courseServices.getCourseById(courseId);

        student.setCourseId(courseDTO.getCourseId());
        courseDTO.getStudents().add(student);

        return studentRepo.save(student);


    }

    /**
     * Drop a student from a course.
     *
     * @param studentId The ID of the student to be dropped.
     * @param courseId The ID of the course the student is to be dropped from.
     * @return The updated Student object after dropping the course.
     * @throws StudentNotFoundByIdException if no student with the given ID is found.
     */
    public Student dropStudentFromCourse(long studentId, long courseId) {
        Student student = studentRepo.findById(studentId).orElse(null);
        if(student == null){
            throw new StudentNotFoundByIdException("Student Not found with Student Id "+studentId+", Unregister from Course Unsuccessfull");
        }

        CourseDTO courseDTO = courseServices.getCourseById(courseId);

        student.setCourseId(0);
        courseDTO.getStudents().remove(student);
        return studentRepo.save(student);
    }

    /**
     * Retrieve all students by course ID.
     * Feign Method used by Course Service
     * @param courseId The ID of the course.
     * @return A list of Student objects enrolled in the course.
     */
    public List<Student> getAllStudentsByCourseId(long courseId) {
        return studentRepo.findByCourseId(courseId);
    }

    @Override
    public String updateStudent(long studentId,Student student) {
        Student student1=studentRepo.findById(studentId).orElse(null);
        student1.setStudentName(student.getStudentName());
        student1.setStudentEmail(student.getStudentEmail());
        studentRepo.save(student1);
        return "Updated";
    }

    @Override
    public CourseDTO getCourseByStudentId(long studentId) {
        Student student=studentRepo.findById(studentId).orElse(null);

        if(student==null){
            throw new StudentNotFoundByIdException("Student Not Found By Id exception");
        }

        long courseId=student.getCourseId();

        if(courseId == 0){
            throw new CourseNotFoundException("Student is not enrolled in any course");
        }

        return courseServices.getCourseByIdForStudent(courseId);
    }


}