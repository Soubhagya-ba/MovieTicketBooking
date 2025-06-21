package com.example.moviebooking.controller;

import com.example.moviebooking.dto.request.MovieShowRequest;
import com.example.moviebooking.entity.Movie;
import com.example.moviebooking.entity.Show;
import com.example.moviebooking.service.MovieShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MovieShowController {

    @Autowired
    private MovieShowService movieShowService;

    @PostMapping("/createShow")
    @PreAuthorize("hasAuthority('THEATER_OWNER')")
    public ResponseEntity<String> addMovieShow(@RequestBody MovieShowRequest request){
      String message = movieShowService.addMovieShow(request);
      return ResponseEntity.ok(message);
    }
    @GetMapping("/getAllShows")
    public ResponseEntity<List<Show>> findAllShows(){
        List<Show> shows = movieShowService.findAllShows();
        return ResponseEntity.status(HttpStatus.CREATED).body(shows);
    }
}
