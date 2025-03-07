package com.project.Course_Service.exception;

public class DeleteCourseByIdException extends RuntimeException{
    public DeleteCourseByIdException(String msg){
        super(msg);
    }
}
