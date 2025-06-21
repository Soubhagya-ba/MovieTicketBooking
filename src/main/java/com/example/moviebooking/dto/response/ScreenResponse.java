package com.example.moviebooking.dto.response;

import com.example.moviebooking.enums.ScreenType;

public record ScreenResponse(
        String screenId,
        String name,
        ScreenType screenType,
        int capacity,
        int noOfRows,
        String createdBy
) {
}
