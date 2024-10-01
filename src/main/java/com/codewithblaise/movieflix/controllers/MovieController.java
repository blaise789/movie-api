package com.codewithblaise.movieflix.controllers;

import java.io.IOException;
import java.util.List;

import com.codewithblaise.movieflix.entities.MoviePageResponse;
import com.codewithblaise.movieflix.exceptions.FileEmptyException;
import com.codewithblaise.movieflix.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        if(file.isEmpty()){
            throw  new FileEmptyException("file field is empty");
        }
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
    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long movieId,@RequestPart MultipartFile file,@RequestPart String updateMovieDto) throws IOException {
        if(file.isEmpty()) file=null;
        MovieDto movieDto=convertToMovieDto(updateMovieDto);
        MovieDto updatedMovie=movieService.updateMovie(movieId,file,movieDto);
        return ResponseEntity.ok(updatedMovie);
    }
    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long movieId) throws IOException {
        return ResponseEntity.ok(movieService.deleteMovie(movieId));
    }
//    with pagination
    @GetMapping("/moviesPage")
    public ResponseEntity<MoviePageResponse> getMoviesWithPagination(@RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber, @RequestParam(defaultValue = AppConstants.PAGE_SIZE ,required = false) Integer pageSize){
        return ResponseEntity.ok(movieService.getAllMoviesByPagination(pageNumber,pageSize));
    }
    @GetMapping("/moviesPageSorted")
    public ResponseEntity<MoviePageResponse> getMoviesWithPaginationAndSorting(@RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,@RequestParam(defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,@RequestParam(defaultValue = AppConstants.SORT_BY)String sortBy,@RequestParam(defaultValue = AppConstants.SORT_DIR) String dir){
        return ResponseEntity.ok(movieService.getAllMoviesByPaginationAndSorting(pageNumber,pageSize,sortBy,dir));
    }

}

