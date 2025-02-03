package com.project.Teacher_Service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerForTeacher {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TeacherNotFoundException.class)
    public Map<String,String> handleTeacherNotFound(TeacherNotFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TeacherNotFoundByIdException.class)
    public Map<String,String> handleTeacherById(TeacherNotFoundByIdException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DeleteTeacherByIdNotFoundException.class)
    public Map<String,String> handleDeleteTeacherById(DeleteTeacherByIdNotFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SaveTeacherException.class)
    public Map<String,String> handleSaveTeacher(SaveTeacherException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CourseIdNotFoundException.class)
    public Map<String,String> handleCourseNotFound(CourseIdNotFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }
}
