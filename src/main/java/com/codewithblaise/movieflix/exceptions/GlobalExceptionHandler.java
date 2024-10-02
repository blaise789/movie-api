package com.codewithblaise.movieflix.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @ExceptionHandler(FileExistException.class)
    public ProblemDetail emptyFileExcHandler(FileExistException exception){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,exception.getMessage());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ProblemDetail UserNotFoundHandler(UsernameNotFoundException exc){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,exc.getMessage());
    }

}
