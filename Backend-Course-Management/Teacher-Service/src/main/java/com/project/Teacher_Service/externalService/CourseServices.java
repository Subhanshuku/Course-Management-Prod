package com.project.Teacher_Service.externalService;

import com.project.Teacher_Service.DTO.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "courseservices",url = "http://localhost:8055/course")
public interface CourseServices {

    @GetMapping("/getByIdForTeacher/{courseId}")
    public CourseDTO getCourseByIdForTeacher(@PathVariable("courseId") long courseId);

}
