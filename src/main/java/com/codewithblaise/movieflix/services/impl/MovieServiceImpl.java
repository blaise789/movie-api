package com.codewithblaise.movieflix.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.codewithblaise.movieflix.dtos.MovieDto;
import com.codewithblaise.movieflix.entities.Movie;
import com.codewithblaise.movieflix.repositories.MovieRepository;
import com.codewithblaise.movieflix.services.FileService;
import com.codewithblaise.movieflix.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
@Autowired
private MovieRepository movieRepository;
@Autowired
private FileService fileService;
@Value("${projects.poster}")
private String path;
@Value("${base.url}")
private String baseUrl;
    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        String fileName=fileService.updloadFile(path,file);
        movieDto.setPoster(fileName);
        
        // Movie movie=Movie.builder()
        //         .movieCast(movieDto.getMovieCast())
        //         .title(movieDto.getTitle())
        //         .director(movieDto.getDirector())
        //         .poster(movieDto.getPoster())
        //         .releaseYear(movieDto.getReleaseYear())
        //         .studio(movieDto.getStudio())
        //         .build();
        Movie movie=new Movie();
        System.out.println(movie.getReleaseYear());
        
        movie.setDirector(movieDto.getDirector());
        movie.setReleaseYear(movieDto.getReleaseYear());
        movie.setMovieCast(movieDto.getMovieCast());
        movie.setMovieCast(movieDto.getMovieCast());
        movie.setTitle(movieDto.getTitle());
    System.out.println(movie);
       Movie savedMovie= movieRepository.save(movie);
       System.out.println(savedMovie);       

    



   String posterUrl=baseUrl+"/file/"+fileName;


        return MovieDto.builder().movieCast(savedMovie.getMovieCast()).title(savedMovie.getTitle()).posterUrl(posterUrl).build();
    }

    @Override
    public MovieDto getMovie(Long movieId) {
        Movie movie=movieRepository.findById(movieId).orElseThrow(()->new RuntimeException("Movie not found"));
        String posterUrl=baseUrl+"/file"+movie.getPoster();
        return MovieDto.builder().title(movie.getTitle()).releaseYear(movie.getReleaseYear()).posterUrl(posterUrl).build();
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies=movieRepository.findAll();
        List<MovieDto> dtos=new ArrayList<>();
        for (Movie movie:movies){
            String posterUrl=baseUrl+"/file/"+movie.getPoster();
            dtos.add(MovieDto.builder().title(movie.getTitle()).director(movie.getDirector()).posterUrl(posterUrl).build());

        }
        return dtos;
    }
}
