package com.example.moviebooking.service;

import com.example.moviebooking.dto.request.ScreenRequest;
import com.example.moviebooking.dto.request.TheaterRequest;
import com.example.moviebooking.dto.response.ScreenResponse;
import com.example.moviebooking.dto.response.TheaterResponse;

import java.util.Map;

public interface TheaterService {

    TheaterResponse createTheater(TheaterRequest request);

    Map<String, String> getAllTheater();

    ScreenResponse addScreen(ScreenRequest request, String theaterId);

    Map<String , String> getAllScreen(String theaterId);

    Map<String, String> getAllSeats(String screenId);
}
