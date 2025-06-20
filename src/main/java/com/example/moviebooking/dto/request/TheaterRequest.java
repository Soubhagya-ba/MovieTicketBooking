package com.example.moviebooking.dto.request;

public record TheaterRequest(
        String name,
        String address,
        String city,
        String landmark
) {
}
