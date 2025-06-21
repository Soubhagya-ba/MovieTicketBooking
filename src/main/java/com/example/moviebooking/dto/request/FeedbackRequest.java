package com.example.moviebooking.dto.request;

public record FeedbackRequest(
        String movieId,
        String theaterId,
        int rating,
        String review
) {
}
