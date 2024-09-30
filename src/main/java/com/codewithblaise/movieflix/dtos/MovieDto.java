package com.codewithblaise.movieflix.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "please provide the movie's  release Year")
    @Min(message = "minimum  year should be atleast 2010",value = 2000)
    private Integer releaseYear;

    private String posterUrl;


}
