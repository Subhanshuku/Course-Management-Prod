package com.externalServices;

import com.dto.TeacherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("TEACHER-SERVICE")
public interface TeacherClient {

    @PostMapping("/teacher/add") //http://localhost:8060/teacher/add
    public TeacherDTO addTeacher(@RequestBody TeacherDTO teacher);

    @DeleteMapping("/teacher/deleteById/{teacherId}") //http://localhost:8060/teacher/deleteById/1
    public  String deleteTeacherById(@PathVariable("teacherId") long teacherId);

    @PutMapping("/teacher/update/{teacherId}")
    public String updateTeacher(@PathVariable long teacherId,@RequestBody TeacherDTO teacher);
}
