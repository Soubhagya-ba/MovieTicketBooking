package com.example.moviebooking.service;

import com.example.moviebooking.dto.request.EditUserRequest;
import com.example.moviebooking.dto.request.UpdatePasswordRequest;
import com.example.moviebooking.dto.request.UserDetailsRequest;
import com.example.moviebooking.dto.request.UserLoginRequest;
import com.example.moviebooking.dto.response.UserDetailsResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface UserDetailsService {

    String login(UserLoginRequest request, HttpServletRequest httpRequest);

    String verifyEmail(String email);

    UserDetailsResponse registerUser(UserDetailsRequest request,int otp);

    String editUserDetails(EditUserRequest request);

    String verifyOtp(int otp , String email);

    String updatePassword(UpdatePasswordRequest request);
}
