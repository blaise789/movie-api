package com.codewithblaise.movieflix.entities;

import com.codewithblaise.movieflix.dtos.MovieDto;

import java.util.List;

public record MoviePageResponse(List<MovieDto> movieRes,Integer pageNumber,Integer pageSize,int totalElements,long totalPages,boolean isLast) {

}
