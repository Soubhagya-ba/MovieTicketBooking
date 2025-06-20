package com.example.moviebooking.dto.mapper;

import com.example.moviebooking.dto.request.TheaterRequest;
import com.example.moviebooking.dto.response.TheaterResponse;
import com.example.moviebooking.entity.Theater;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TheaterMapper {

    public Theater toEntity(TheaterRequest source, Theater target){
        target.setName(source.name());
        target.setAddress(source.address());
        target.setCity(source.city());
        target.setLandmark(source.landmark());
        target.setCreatedAt(LocalDateTime.now());
        target.setUpdatedAt(LocalDateTime.now());
        return target;
    }

    public TheaterResponse toResponse(Theater theater){
        return new TheaterResponse(
                theater.getTheaterId(),
                theater.getName(),
                theater.getAddress(),
                theater.getCity(),
                theater.getLandmark(),
                theater.getCreatedBy()
        );
    }
}
