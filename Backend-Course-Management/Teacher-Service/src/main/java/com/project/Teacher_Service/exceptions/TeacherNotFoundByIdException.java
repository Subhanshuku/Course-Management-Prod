package com.project.Teacher_Service.exceptions;

public class TeacherNotFoundByIdException extends RuntimeException{
    public TeacherNotFoundByIdException(String msg){
        super(msg);
    }
}
