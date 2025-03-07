package com.project.Course_Service.exception;

public class CourseAlreadyPresent extends RuntimeException{
    public CourseAlreadyPresent(String msg){
        super(msg);
    }
}
