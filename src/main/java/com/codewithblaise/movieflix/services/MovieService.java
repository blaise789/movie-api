package com.codewithblaise.movieflix.services;

import com.codewithblaise.movieflix.dtos.MovieDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException;
    MovieDto getMovie();
    List<MovieDto> getAllMovies();

}
