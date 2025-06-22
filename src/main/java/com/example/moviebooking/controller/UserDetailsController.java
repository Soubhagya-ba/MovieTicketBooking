package com.example.moviebooking.controller;

import com.example.moviebooking.dto.request.EditUserRequest;
import com.example.moviebooking.dto.request.UpdatePasswordRequest;
import com.example.moviebooking.dto.request.UserDetailsRequest;
import com.example.moviebooking.dto.request.UserLoginRequest;
import com.example.moviebooking.dto.response.UserDetailsResponse;
import com.example.moviebooking.service.UserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/userLogin")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request, HttpServletRequest httpRequest){
      String message = userDetailsService.login(request,httpRequest);
      return ResponseEntity.ok(message);
    }

    @GetMapping("/send-otp")
    public ResponseEntity<String> verifyEmail(@RequestParam String email){
        String message = userDetailsService.verifyEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @PostMapping("/register")
    public ResponseEntity<UserDetailsResponse> registerUser(@RequestBody UserDetailsRequest request,@RequestParam int otp){
      UserDetailsResponse userDetailsResponse = userDetailsService.registerUser(request,otp);
      return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsResponse);
    }
    @PostMapping("/editUser")
    public ResponseEntity<String> editUserDetails(@RequestBody EditUserRequest request){
        String message = userDetailsService.editUserDetails(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @GetMapping("/verify-Otp")
    public ResponseEntity<String> verifyOtp(@RequestParam int otp, @RequestParam String email){
       String message = userDetailsService.verifyOtp(otp,email);
       return ResponseEntity.ok(message);
    }
    @PostMapping("/update/password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest request){
        String message = userDetailsService.updatePassword(request);
        return ResponseEntity.ok(message);
    }
}
