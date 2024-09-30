package com.codewithblaise.movieflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MovieNotFoundException.class)
    public ProblemDetail movieNotFoundHandler(MovieNotFoundException exc){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,exc.getMessage());
    }
    @ExceptionHandler(FileEmptyException.class)
    public ProblemDetail emptyFileExcHandler(FileEmptyException exc){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,exc.getMessage());
    }

}
