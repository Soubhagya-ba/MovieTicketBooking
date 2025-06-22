package com.example.moviebooking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MovieShowNotFoundException extends RuntimeException {
    private final String message;
}
