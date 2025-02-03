package com.project.Course_Service.exception;

public class NoCourseFoundByIdException extends RuntimeException{
    public NoCourseFoundByIdException(String msg){
        super(msg);
    }
}
