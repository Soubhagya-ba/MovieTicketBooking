package com.example.moviebooking.dto.response;

import com.example.moviebooking.enums.Certificate;
import com.example.moviebooking.enums.Genre;

import java.time.Duration;

public record MovieResponse(
        String movieId,
        String title,
        String description,
        String[] cast,
        Duration runtime,
        Certificate certificate,
        Genre genre
) {
}
