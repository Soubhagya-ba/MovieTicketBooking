package com.example.moviebooking.controller;

import com.example.moviebooking.dto.request.MovieRequest;
import com.example.moviebooking.dto.response.MovieResponse;
import com.example.moviebooking.entity.Movie;
import com.example.moviebooking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    @PreAuthorize("hasAuthority('THEATER_OWNER')")
    public ResponseEntity<MovieResponse> addMovie(@RequestBody MovieRequest request)
    {
       MovieResponse response = movieService.addMovie(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/addMultipleMovie")
    @PreAuthorize("hasAuthority('THEATER_OWNER')")
    public ResponseEntity<List<MovieResponse>> addMultipleMovie(@RequestBody List<MovieRequest> request)
    {
       List<MovieResponse> responses = movieService.addMultipleMovie(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }



    @GetMapping("/getMovie")
    @PreAuthorize("hasAuthority('THEATER_OWNER')")
    public ResponseEntity<Map<String , String>> getMovieIdName(){
        Map<String , String> moviesMap = movieService.getMovieIdName();
        return ResponseEntity.status(HttpStatus.CREATED).body(moviesMap);
    }
    @GetMapping("/getAllMovies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.status(HttpStatus.CREATED).body(movies);
    }
}
