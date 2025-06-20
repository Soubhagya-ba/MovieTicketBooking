package com.example.moviebooking.dto.request;

public record UserLoginRequest(
        String email,
        String password
) {
}
