package com.project.Student_Service.externalServices;

import com.project.Student_Service.DTO.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "courseservices",url = "http://localhost:8055/course")
public interface CourseServices {

    @GetMapping("/getById/{id}")
    public CourseDTO getCourseById(@PathVariable("id") long CourseId);

    @GetMapping("/getByIdForStudent/{courseId}")
    public CourseDTO getCourseByIdForStudent(@PathVariable long courseId);

}
