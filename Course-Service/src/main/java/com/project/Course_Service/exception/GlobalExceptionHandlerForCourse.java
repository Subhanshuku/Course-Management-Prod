package com.project.Course_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerForCourse {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoCourseFoundException.class)
    public Map<String,String> handleNoCourseFound(NoCourseFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoCourseFoundByIdException.class)
    public Map<String,String> handleNoCourseByIdFound(NoCourseFoundByIdException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SaveCourseException.class)
    public Map<String,String> handleSaveCourse(SaveCourseException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DeleteCourseByIdException.class)
    public Map<String,String> handleDeleteCourse(DeleteCourseByIdException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }
}
