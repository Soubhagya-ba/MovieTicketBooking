package com.example.moviebooking.service;

import com.example.moviebooking.dto.request.MovieRequest;
import com.example.moviebooking.dto.response.MovieResponse;
import com.example.moviebooking.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {
    MovieResponse addMovie(MovieRequest request);
    Map<String, String> getMovieIdName();

    List<Movie> getAllMovies();

    List<MovieResponse> addMultipleMovie(List<MovieRequest> request);
}
