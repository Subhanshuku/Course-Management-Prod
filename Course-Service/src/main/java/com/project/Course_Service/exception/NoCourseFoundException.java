package com.project.Course_Service.exception;

public class NoCourseFoundException extends RuntimeException{

    public NoCourseFoundException(String msg){
        super(msg);
    }
}
