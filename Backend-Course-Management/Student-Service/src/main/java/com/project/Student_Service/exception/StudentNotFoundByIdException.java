package com.project.Student_Service.exception;

public class StudentNotFoundByIdException extends RuntimeException{

    public StudentNotFoundByIdException(String msg){
        super(msg);
    }
}
