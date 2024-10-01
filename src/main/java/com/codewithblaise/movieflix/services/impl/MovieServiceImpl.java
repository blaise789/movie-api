package com.codewithblaise.movieflix.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.codewithblaise.movieflix.entities.MoviePageResponse;
import com.codewithblaise.movieflix.exceptions.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        String filePath=path+File.separator+file.getOriginalFilename();
        if (Files.exists(Paths.get(filePath))){
            throw new FileAlreadyExistsException("file already exist");

        }
        String fileName=fileService.updloadFile(path,file);
        movieDto.setPoster(fileName);

         Movie movie=Movie.builder()
                 .movieCast(movieDto.getMovieCast())
                 .title(movieDto.getTitle())
                 .director(movieDto.getDirector())
                 .poster(movieDto.getPoster())
                 .releaseYear(movieDto.getReleaseYear())
                 .studio(movieDto.getStudio())
                 .build();
//        Movie movie=new Movie();
//        System.out.println(movie.getReleaseYear());
//
//        movie.setDirector(movieDto.getDirector());
//        movie.setReleaseYear(movieDto.getReleaseYear());
//        movie.setMovieCast(movieDto.getMovieCast());
//        movie.setMovieCast(movieDto.getMovieCast());
//        movie.setTitle(movieDto.getTitle());
//    System.out.println(movie);
       Movie savedMovie= movieRepository.save(movie);
       System.out.println(savedMovie);       

    



   String posterUrl=baseUrl+"file/"+fileName;


        return MovieDto.builder().movieCast(savedMovie.getMovieCast()).title(savedMovie.getTitle()).releaseYear(movie.getReleaseYear()).posterUrl(posterUrl).releaseYear(movie.getReleaseYear()).director(movie.getDirector()).build();
    }

    @Override
    public MovieDto getMovie(Long movieId) {
        Movie movie=movieRepository.findById(movieId).orElseThrow(()->new MovieNotFoundException("Movie not found"));
        String posterUrl=baseUrl+"file/"+movie.getPoster();
        return MovieDto.builder().title(movie.getTitle()).releaseYear(movie.getReleaseYear()).posterUrl(posterUrl).build();
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies=movieRepository.findAll();
        List<MovieDto> dtos=new ArrayList<>();
        for (Movie movie:movies){
            String posterUrl=baseUrl+"file/"+movie.getPoster();
            dtos.add(MovieDto.builder().title(movie.getTitle()).director(movie.getDirector()).posterUrl(posterUrl).build());

        }
        return dtos;
    }

    @Override

    public MovieDto updateMovie(Long movieId,MultipartFile file,MovieDto updatedMovieDto) throws IOException {
        Movie movieExists=movieRepository.findById(movieId).orElseThrow(()->new MovieNotFoundException("movie not found"));
        String fileName=movieExists.getPoster();
       if(file !=null){
           System.out.println("deleting the file");
            Path filePath=Paths.get(path+ File.separator+fileName);
            Files.deleteIfExists(filePath);
            System.out.println("exists");
            fileName=fileService.updloadFile(path,file);
       }
       movieExists.setMovieId(movieId);
       movieExists.setPoster(fileName);
       movieExists.setTitle(updatedMovieDto.getTitle());
       movieExists.setMovieCast(updatedMovieDto.getMovieCast());
       movieExists.setStudio(updatedMovieDto.getStudio());
       movieExists.setDirector(updatedMovieDto.getDirector());
       Movie updatedMovie=movieRepository.save(movieExists);

       String posterUrl=baseUrl+"file/"+fileName;


        return MovieDto.builder().title(updatedMovie.getTitle()).releaseYear(updatedMovie.getReleaseYear()).posterUrl(posterUrl).build();

    }
    public String deleteMovie(Long movieId) throws IOException {
        Movie movieExists=movieRepository.findById(movieId).orElseThrow(()->new MovieNotFoundException("movie not found"));
        Files.deleteIfExists(Paths.get(path+File.separator+movieExists.getPoster()));
        movieRepository.delete(movieExists);
        return "movie delete successfully";

    }

    @Override
    public MoviePageResponse getAllMoviesByPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Movie> moviePages=movieRepository.findAll(pageable);
        List<Movie> movies=moviePages.getContent();
        List<MovieDto> dtos=new ArrayList<>();
        for (Movie movie:movies){
            String posterUrl=baseUrl+"file/"+movie.getPoster();
            dtos.add(MovieDto.builder().title(movie.getTitle()).director(movie.getDirector()).posterUrl(posterUrl).build());

        }


        return new MoviePageResponse(dtos,pageNumber,pageSize,moviePages.getTotalPages(),moviePages.getTotalElements(),moviePages.isLast());
    }

    @Override
    public MoviePageResponse getAllMoviesByPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String dir) {
        Sort sort=dir.equalsIgnoreCase("asc")?Sort.by(dir).ascending():Sort.by(dir).descending();
        Pageable pageable=PageRequest.of(pageNumber,pageSize,sort);
        Page<Movie> moviePages=movieRepository.findAll(pageable);
        List<Movie> movies=moviePages.getContent();
        List<MovieDto> dtos=new ArrayList<>();
        for (Movie movie:movies){
            String posterUrl=baseUrl+"file/"+movie.getPoster();
            dtos.add(MovieDto.builder().title(movie.getTitle()).director(movie.getDirector()).posterUrl(posterUrl).build());

        }


        return new MoviePageResponse(dtos,pageNumber,pageSize,moviePages.getTotalPages(),moviePages.getTotalElements(),moviePages.isLast());

    }


}
