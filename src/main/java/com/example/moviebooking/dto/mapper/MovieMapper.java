package com.example.moviebooking.dto.mapper;

import com.example.moviebooking.dto.request.MovieRequest;
import com.example.moviebooking.dto.response.MovieResponse;
import com.example.moviebooking.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public static Movie toEntity(MovieRequest source, Movie target){
        target.setTitle(source.title());
        target.setDescription(source.description());
        target.setCast(source.cast());
        target.setCertificate(source.certificate());
        target.setGenre(source.genre());
        target.setRuntime(source.runtime());
        return target;
    }

    public static MovieResponse toResponse(Movie movie){
        return new MovieResponse(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getCast(),
                movie.getRuntime(),
                movie.getCertificate(),
                movie.getGenre()
        );
    }

    public static List<Movie> toEntity(List<MovieRequest> source){
        List<Movie> movies = new ArrayList<>();
        for (MovieRequest m : source){
            Movie movie = new Movie();
            movie.setTitle(m.title());
            movie.setDescription(m.description());
            movie.setCast(m.cast());
            movie.setCertificate(m.certificate());
            movie.setGenre(m.genre());
            movie.setRuntime(m.runtime());
            movies.add(movie);
        }
        return movies;
    }

    public static List<MovieResponse> toResponse(List<Movie> movies){
        List<MovieResponse> responses = new ArrayList<>();
        for (Movie movie : movies) {
            responses.add(toResponse(movie));
        }
        return responses;
    }
}
