package com.example.moviebooking.service;

import com.example.moviebooking.dto.request.MovieShowRequest;
import com.example.moviebooking.entity.Show;

import java.util.List;
import java.util.Map;

public interface MovieShowService {

    String addMovieShow(MovieShowRequest request);

    List<Show> findAllShows();
}
