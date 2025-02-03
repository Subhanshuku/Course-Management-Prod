package com.project.Teacher_Service.exceptions;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException(String msg){
        super(msg);
    }
}
