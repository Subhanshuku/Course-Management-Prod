package com.project.Admin_Service.externalService;

import com.project.Admin_Service.DTO.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "courseservice")
public interface CourseService {

    @GetMapping("/course/getById/{courseId}")  //http://localhost:8055/course/getById/1
    public CourseDTO getCourseById(@PathVariable("courseId") long courseId);
}
