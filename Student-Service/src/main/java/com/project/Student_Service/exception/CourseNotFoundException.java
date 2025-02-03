package com.project.Student_Service.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String msg){
        super(msg);
    }
}
