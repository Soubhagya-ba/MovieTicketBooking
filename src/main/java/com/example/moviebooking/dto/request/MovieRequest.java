package com.example.moviebooking.dto.request;

import com.example.moviebooking.enums.Certificate;
import com.example.moviebooking.enums.Genre;

import java.time.Duration;

public record MovieRequest(
        String title,
        String description,
        String[] cast,
        Duration runtime,
        Certificate certificate,
        Genre genre
) {
}
