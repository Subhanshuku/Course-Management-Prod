package com.project.Teacher_Service.service;

import com.project.Teacher_Service.DTO.CourseDTO;
import com.project.Teacher_Service.DTO.TeacherDTO;
import com.project.Teacher_Service.entity.Teacher;
import com.project.Teacher_Service.exceptions.DeleteTeacherByIdNotFoundException;
import com.project.Teacher_Service.exceptions.TeacherAlreadyPresent;
import com.project.Teacher_Service.exceptions.TeacherNotFoundByIdException;
import com.project.Teacher_Service.exceptions.TeacherNotFoundException;
import com.project.Teacher_Service.externalService.CourseServices;
import com.project.Teacher_Service.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    // Inject the TeacherRepo instance using Spring's @Autowired annotation
    @Autowired
    TeacherRepo teacherRepo;

    // Inject the CourseServices instance, which is a Feign client, using Spring's @Autowired annotation
    @Autowired
    CourseServices courseServices;

    /**
     * Add a new teacher.
     *
     * @param teacher The Teacher object to be added.
     * @return The added Teacher object.
     */
    public TeacherDTO addStudent(Teacher teacher) {
        Teacher teacher1=teacherRepo.findById(teacher.getTeacherId()).orElse(null);
        if(teacher1!=null){
            throw new TeacherAlreadyPresent("Teacher is already present with id "+teacher1.getTeacherId());
        }
        Teacher teacher2 = teacherRepo.save(teacher);
        TeacherDTO dto=new TeacherDTO(teacher2.getTeacherId(),teacher2.getTeacherName(),teacher2.getTeacherEmail());
        return dto;
    }

    /**
     * Retrieve all teachers.
     *
     * @return A list of all Teacher objects.
     * @throws TeacherNotFoundException if there are no teachers present.
     */
    public List<Teacher> getAllTeacher() {
        List<Teacher> teachers = teacherRepo.findAll();
        if(teachers.isEmpty()){
            throw new TeacherNotFoundException("Teachers record not found");
        }
        return teachers;
    }

    /**
     * Retrieve a teacher by their ID.
     *
     * @param teacherId The ID of the teacher to be retrieved.
     * @return The Teacher object corresponding to the given ID.
     * @throws TeacherNotFoundByIdException if no teacher with the given ID is found.
     */
    public Teacher getTeacherById(long teacherId) {
        Teacher teacher = teacherRepo.findById(teacherId).orElse(null);
        if(teacher == null){
            throw new TeacherNotFoundByIdException("Teacher Not found by Id "+teacherId);
        }
        return teacher;
    }

    /**
     * Delete a teacher by their ID.
     *
     * @param teacherId The ID of the teacher to be deleted.
     * @return A message indicating the result of the deletion.
     * @throws DeleteTeacherByIdNotFoundException if no teacher with the given ID is found.
     */
    public String deleteTeacherById(long teacherId) {
       Teacher teacher=teacherRepo.findById(teacherId).orElse(null);
       if(teacher==null){
           throw new DeleteTeacherByIdNotFoundException("Teacher Delete Unsuccessfull");
       }
        teacherRepo.deleteById(teacherId);
        return "Teacher Deleted Successfully";

    }

    /**
     * Retrieve the course taught by a teacher based on their ID.
     *
     * @param teacherId The ID of the teacher.
     * @return The CourseDTO object representing the course taught by the teacher.
     * @throws TeacherNotFoundByIdException if no teacher with the given ID is found.
     */
    public CourseDTO getTaughtCourseByTeacherId(long teacherId) {
        Teacher teacher=teacherRepo.findById(teacherId).orElse(null);
        if(teacher==null){
            throw new TeacherNotFoundByIdException("Teacher not found by Id "+teacherId);
        }
        long courseId=teacher.getCourseId();
        CourseDTO courseDTO=courseServices.getCourseByIdForTeacher(courseId);

        return courseDTO;
    }

    /**
     * Assign a course to a teacher.
     *
     * @param teacherId The ID of the teacher.
     * @param courseId The ID of the course to be assigned.
     * @return The updated Teacher object after assignment.
     * @throws TeacherNotFoundByIdException if no teacher with the given ID is found.
     */
    public Teacher assignCourseToTeacher(long teacherId, long courseId) {
        Teacher teacher=teacherRepo.findById(teacherId).orElse(null);
        if(teacher==null){
            throw new TeacherNotFoundByIdException("Teacher not found by Id "+teacherId);
        }
        CourseDTO courseDTO=courseServices.getCourseByIdForTeacher(courseId);
        teacher.setCourseId(courseDTO.getCourseId());

        return teacherRepo.save(teacher);
    }

    @Override
    public Teacher dropTeacherFromCourse(long teacherId, long courseId) {
        Teacher teacher=teacherRepo.findById(teacherId).orElse(null);
        if(teacher==null){
            throw new TeacherNotFoundByIdException("Teacher not found by Id "+teacherId);
        }
        //CourseDTO courseDTO=courseServices.getCourseByIdForTeacher(courseId);
        teacher.setCourseId(0);

        return teacherRepo.save(teacher);
    }

    @Override
    public String updateTeacher(long teacherId, Teacher teacher) {
        Teacher teacher1=teacherRepo.findById(teacherId).orElse(null);
        teacher1.setTeacherName(teacher.getTeacherName());
        teacher1.setTeacherEmail(teacher.getTeacherEmail());
        teacherRepo.save(teacher1);
        return "updated";
    }

}
