package com.project.Student_Service.repo;

import com.project.Student_Service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    List<Student> findByCourseId(long courseId);
}
