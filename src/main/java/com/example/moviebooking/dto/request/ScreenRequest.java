package com.example.moviebooking.dto.request;

import com.example.moviebooking.enums.ScreenType;

public record ScreenRequest(
        ScreenType screenType,
        int capacity,
        int noOfRows

) {
}
