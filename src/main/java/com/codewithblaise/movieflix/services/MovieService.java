package com.codewithblaise.movieflix.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.codewithblaise.movieflix.dtos.MovieDto;

public interface MovieService {
    MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException;
    MovieDto getMovie(Long movieId);
    List<MovieDto> getAllMovies();


    MovieDto updateMovie(Long movieId,MultipartFile file,MovieDto updateMovieDto) throws IOException;
    String deleteMovie(Long movieId) throws  IOException;
}
