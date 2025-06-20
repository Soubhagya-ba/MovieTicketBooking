package com.example.moviebooking.dto.mapper;

import com.example.moviebooking.dto.request.UserDetailsRequest;
import com.example.moviebooking.dto.response.UserDetailsResponse;
import com.example.moviebooking.entity.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserDetailsMapper {

    public UserDetails toEntity(UserDetailsRequest source, UserDetails target){
        target.setName(source.name());
        target.setEmail(source.email());
        target.setPassword(source.password());
        target.setPhoneNo(source.phoneNo());
        target.setUserRole(source.userRole());
        target.setDateOfBirth(source.dateOfBirth());
        target.setCreatedAt(LocalDateTime.now());
        target.setUpdateAt(LocalDateTime.now());
        return target;
    }

    public UserDetailsResponse toResponse(UserDetails details){
        return new UserDetailsResponse(
                details.getUserId(),
                details.getName(),
                details.getEmail(),
                details.getPhoneNo(),
                details.getUserRole(),
                details.getDateOfBirth()
        );
    }
}
