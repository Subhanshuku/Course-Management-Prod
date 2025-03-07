package com.project.Course_Service.externalServices;

import com.project.Course_Service.DTO.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "studentservices",url = "http://localhost:8050/student")
public interface StudentServices {

    @GetMapping("/getAllStudentsByCourseId/{courseId}")
    public List<StudentDTO> getAllStudentsByCourseId(@PathVariable long courseId);

}
