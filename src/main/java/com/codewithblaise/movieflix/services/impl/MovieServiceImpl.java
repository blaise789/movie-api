package com.codewithblaise.movieflix.services.impl;

import com.codewithblaise.movieflix.dtos.MovieDto;
import com.codewithblaise.movieflix.entities.Movie;
import com.codewithblaise.movieflix.repositories.MovieRepository;
import com.codewithblaise.movieflix.services.FileService;
import com.codewithblaise.movieflix.services.MovieService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

private MovieRepository movieRepository;
private FileService fileService;
@Value("${projects.poster}")
private String path;
    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        String fileName=fileService.updloadFile(path,file);
         movieDto.setPoster(fileName);
        Movie movie=Movie.builder().movieCast(movieDto.getMovieCast()).title(movieDto.getTitle()).director(movieDto.getDirector()).poster(movieDto.getPoster()).build();





        return null;
    }

    @Override
    public MovieDto getMovie() {
        return null;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return List.of();
    }
}
