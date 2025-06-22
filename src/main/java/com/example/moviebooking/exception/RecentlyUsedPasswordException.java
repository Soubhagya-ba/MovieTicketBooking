package com.example.moviebooking.exception;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RecentlyUsedPasswordException extends RuntimeException {
    private final String message;
}
