package com.project.Teacher_Service.exceptions;

public class TeacherAlreadyPresent extends RuntimeException{
    public TeacherAlreadyPresent(String msg){
        super(msg);
    }
}
