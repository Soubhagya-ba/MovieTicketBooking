package com.example.moviebooking.dto.request;

public record UpdatePasswordRequest(
        String password,
        String confirmPassword
) {
}
