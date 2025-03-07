package com.project.Student_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerForStudent {

    @ResponseStatus(HttpStatus.NOT_FOUND)
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
    @ExceptionHandler(DeleteStudentByIdException.class)
    public Map<String,String> handleDeleteStudentById(DeleteStudentByIdException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StudentAlreadyPresent.class)
    public Map<String,String> handleStudentAlreadyPresent(StudentAlreadyPresent exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CourseNotFoundException.class)
    public Map<String,String> handleCourseNotPresent(CourseNotFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("ErrorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationError(MethodArgumentNotValidException exception){
        Map<String,String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

}
