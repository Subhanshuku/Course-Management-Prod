package com.project.Student_Service.exception;

public class StudentAlreadyPresent extends RuntimeException{
    public StudentAlreadyPresent(String msg){
        super(msg);
    }
}
