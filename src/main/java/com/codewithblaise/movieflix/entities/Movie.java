package com.codewithblaise.movieflix.entities;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    @Column(nullable = false,length = 200)
    @NotBlank(message = "please provide the movie's title")
    private String title;
    @Column(nullable = false,length = 200)
    @NotBlank(message = "please provide the movie's director")
    private String director;
    @Column(nullable = false,length = 200)
    @NotBlank(message = "please provide the movie's studio")
    private String studio;
    @Column(nullable = false,length = 200)
    @NotBlank(message = "please provide the movie's poster")
    private String poster;
    @ElementCollection
    @CollectionTable(name = "movie_cast",joinColumns=@JoinColumn(name="movie_id"))
    private Set<String> movieCast;
    @Column(nullable = false,length = 200)
    @NotBlank(message = "please provide the movie's  release Year")
    private Integer releaseYear;




}
