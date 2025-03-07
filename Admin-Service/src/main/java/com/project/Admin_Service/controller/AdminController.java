package com.project.Admin_Service.controller;

import com.project.Admin_Service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PutMapping("/enrollStudent/{studentId}/{courseId}")
    public String enrollStudentIntoCourse(@PathVariable long studentId,@PathVariable long courseId){
        return adminService.enrollStudent(studentId,courseId);
    }
}
