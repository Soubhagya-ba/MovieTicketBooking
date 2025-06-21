package com.example.moviebooking.dto.request;

import java.time.LocalDateTime;

public record MovieShowRequest(
        String screenId,
        String theaterId,
        String movieId,
        LocalDateTime startsAt
) {
}
