package com.externalServices;

import com.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("STUDENT-SERVICE")
public interface StudentClient {

    @PostMapping("/student/add") //http://localhost:8050/student/add
    public StudentDTO addStudent(@RequestBody StudentDTO student);

    @DeleteMapping("/student/deleteById/{studentId}")  //http://localhost:8050/student/deleteById/1
    public String deleteStudentById(@PathVariable("studentId") long studentId);

    @PutMapping("/student/update/{studentId}")
    public String updateStudent(@PathVariable long studentId,@RequestBody StudentDTO student);
}
