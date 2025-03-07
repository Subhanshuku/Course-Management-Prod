package com.project.Admin_Service.externalService;

import com.project.Admin_Service.DTO.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="studentservice")
public interface StudentService {

    @GetMapping("/student/getById/{studentId}")
    public StudentDTO getStudentById(@PathVariable("studentId") long studentId);

}
