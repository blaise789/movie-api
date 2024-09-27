package com.codewithblaise.movieflix.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

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
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;
    @Column(nullable = false,length = 200)
    @NotBlank(message = "please provide the movie's  release Year")
    private Integer releaseYear;




}
