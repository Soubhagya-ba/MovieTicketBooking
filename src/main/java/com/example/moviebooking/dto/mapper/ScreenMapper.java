package com.example.moviebooking.dto.mapper;

import com.example.moviebooking.dto.request.ScreenRequest;
import com.example.moviebooking.dto.response.ScreenResponse;
import com.example.moviebooking.entity.Screen;

import java.time.LocalDateTime;

public class ScreenMapper {

    public static Screen toEntity(ScreenRequest source, Screen target){
        target.setName(source.name());
        target.setScreenType(source.screenType());
        target.setCapacity(source.capacity());
        target.setNoOfRows(source.noOfRows());
        target.setCreatedAt(LocalDateTime.now());
        target.setUpdateAt(LocalDateTime.now());
        return target;
    }
    public static ScreenResponse toResponse(Screen screen){
        return new ScreenResponse(
                screen.getScreenId(),
                screen.getName(),
                screen.getScreenType(),
                screen.getCapacity(),
                screen.getNoOfRows(),
                screen.getCreatedBy()
        );
    }
}
