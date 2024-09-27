package com.codewithblaise.movieflix.controllers;

import com.codewithblaise.movieflix.dtos.MovieDto;
import com.codewithblaise.movieflix.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private MovieService movieService;

    @PostMapping()
    public MovieDto createMovie(@RequestPart MultipartFile file,@RequestBody MovieDto movieDto) throws IOException {
        MovieDto movie=movieService.addMovie(movieDto,file);

        return movie;
    }
    @GetMapping()
    public MovieDto getMovie(){
        return null;

    }
    @GetMapping()
    public List<MovieDto>  getAllMovies(){
        return null;
    }

}
