package com.codewithblaise.movieflix.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithblaise.movieflix.dtos.MovieDto;
import com.codewithblaise.movieflix.services.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<MovieDto> createMovie(@RequestPart MultipartFile file,@RequestPart String movieDto) throws IOException {
    MovieDto dto=convertToMovieDto(movieDto);
    return new ResponseEntity<>(movieService.addMovie(dto,file), HttpStatus.CREATED);
    }
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(movieService.getMovie(movieId));

    }
    @GetMapping()
    public ResponseEntity<List<MovieDto>>  getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    private MovieDto convertToMovieDto(String movieDtoObj) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.readValue(movieDtoObj,MovieDto.class);
    }
}

