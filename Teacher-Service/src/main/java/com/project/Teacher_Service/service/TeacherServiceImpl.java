package com.project.Teacher_Service.service;

import com.project.Teacher_Service.DTO.CourseDTO;
import com.project.Teacher_Service.entity.Teacher;
import com.project.Teacher_Service.exceptions.DeleteTeacherByIdNotFoundException;
import com.project.Teacher_Service.exceptions.SaveTeacherException;
import com.project.Teacher_Service.exceptions.TeacherNotFoundByIdException;
import com.project.Teacher_Service.exceptions.TeacherNotFoundException;
import com.project.Teacher_Service.externalService.CourseServices;
import com.project.Teacher_Service.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    CourseServices courseServices;

    public Teacher addStudent(Teacher teacher) {
        try{
            return teacherRepo.save(teacher);
        }catch(Exception e){
            throw new SaveTeacherException("Teacher Not Saved");
        }
    }

    public List<Teacher> getAllTeacher() {
        List<Teacher> teachers = teacherRepo.findAll();
        if(teachers.isEmpty()){
            throw new TeacherNotFoundException("Teachers record not found");
        }
        return teachers;
    }

    public Teacher getTeacherById(long teacherId) {
        Teacher teacher = teacherRepo.findById(teacherId).orElse(null);
        if(teacher == null){
            throw new TeacherNotFoundByIdException("Teacher Not found by Id "+teacherId);
        }
        return teacher;
    }

    public String deleteTeacherById(long teacherId) {
       Teacher teacher=teacherRepo.findById(teacherId).orElse(null);
       if(teacher==null){
           throw new DeleteTeacherByIdNotFoundException("Teacher Delete Unsuccessfull");
       }
        teacherRepo.deleteById(teacherId);
        return "Teacher Deleted Successfully";

    }

    public CourseDTO getTaughtCourseByTeacherId(long teacherId) {
        Teacher teacher=teacherRepo.findById(teacherId).orElse(null);
        if(teacher==null){
            throw new TeacherNotFoundByIdException("Teacher not found by Id "+teacherId);
        }
        long courseId=teacher.getCourseId();
        CourseDTO courseDTO=courseServices.getCourseByIdForTeacher(courseId);

        return courseDTO;
    }

    public Teacher assignCourseToTeacher(long teacherId, long courseId) {
        Teacher teacher=teacherRepo.findById(teacherId).orElse(null);
        if(teacher==null){
            throw new TeacherNotFoundByIdException("Teacher not found by Id "+teacherId);
        }
        CourseDTO courseDTO=courseServices.getCourseByIdForTeacher(courseId);
        teacher.setCourseId(courseDTO.getCourseId());

        return teacherRepo.save(teacher);
    }
}
