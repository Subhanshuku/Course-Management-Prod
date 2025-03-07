package com.project.Teacher_Service.repo;

import com.project.Teacher_Service.DTO.CourseDTO;
import com.project.Teacher_Service.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Long> {
    List<CourseDTO> findAllByTeacherId(long teacherId);
}
