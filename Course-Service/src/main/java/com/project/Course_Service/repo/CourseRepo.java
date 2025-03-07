package com.project.Course_Service.repo;

import com.project.Course_Service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {
    //List<Course> findAllByStudentId(Long studentId);
}
