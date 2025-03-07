package com.project.Student_Service.exception;

public class DeleteStudentByIdException extends RuntimeException{

    public DeleteStudentByIdException(String msg){
        super(msg);
    }
}
