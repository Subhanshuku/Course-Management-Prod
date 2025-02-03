package com.project.Student_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerForStudent {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StudentNotFoundException.class)
    public Map<String,String> handleStudentNotFound(StudentNotFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StudentNotFoundByIdException.class)
    public Map<String,String> handleStudentNotFoundById(StudentNotFoundByIdException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SaveStudentException.class)
    public Map<String,String> handleSaveStudent(SaveStudentException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DeleteStudentByIdException.class)
    public Map<String,String> handleDeleteStudentById(DeleteStudentByIdException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CourseNotFoundException.class)
    public Map<String,String> handleCourseNotFound(CourseNotFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

}
