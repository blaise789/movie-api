package com.codewithblaise.movieflix.dtos;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
    @NotBlank(message = "please provide the movie's title")
    private String title;
    @NotBlank(message = "please provide the movie's director")
    private String director;
    @NotBlank(message = "please provide the movie's studio")
    private String studio;
    private String poster;
    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;
    @NotBlank(message = "please provide the movie's  release Year")
    private Integer releaseYear;
    private String posterUrl;


}
