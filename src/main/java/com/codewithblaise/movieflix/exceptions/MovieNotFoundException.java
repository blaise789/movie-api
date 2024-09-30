package com.codewithblaise.movieflix.exceptions;

public class MovieNotFoundException  extends RuntimeException{

    public MovieNotFoundException(String movieNotFound) {
        super(movieNotFound);
    }
}
