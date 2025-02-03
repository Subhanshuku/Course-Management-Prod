package com.project.Teacher_Service.exceptions;

public class DeleteTeacherByIdNotFoundException extends RuntimeException{
    public DeleteTeacherByIdNotFoundException(String msg){
        super(msg);
    }
}
