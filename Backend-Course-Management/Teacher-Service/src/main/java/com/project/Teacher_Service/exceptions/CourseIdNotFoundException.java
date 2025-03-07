package com.project.Teacher_Service.exceptions;

public class CourseIdNotFoundException extends RuntimeException{
    public CourseIdNotFoundException(String msg){
        super(msg);
    }
}
