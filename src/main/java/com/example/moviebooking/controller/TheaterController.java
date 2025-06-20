package com.example.moviebooking.controller;

import com.example.moviebooking.dto.request.ScreenRequest;
import com.example.moviebooking.dto.request.TheaterRequest;
import com.example.moviebooking.dto.response.ScreenResponse;
import com.example.moviebooking.dto.response.TheaterResponse;
import com.example.moviebooking.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("/theater")
    @PreAuthorize("hasAuthority('THEATER_OWNER')")
    public ResponseEntity<TheaterResponse> createTheater(@RequestBody TheaterRequest request){
       TheaterResponse response = theaterService.createTheater(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/getAllTheater")
    public ResponseEntity<Map<String, String>> getAllTheater(){
        Map<String,String> map = theaterService.getAllTheater();
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }
    @PostMapping("/screen")
    @PreAuthorize("hasAuthority('THEATER_OWNER')")
    public ResponseEntity<ScreenResponse> addScreenToTheater(@RequestBody ScreenRequest request, @RequestParam String theaterId){
       ScreenResponse response = theaterService.addScreen(request,theaterId);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/getAllScreen")
    public ResponseEntity<Map<String , String>> getAllScreen(@RequestParam String theaterId){
        Map<String , String > map = theaterService.getAllScreen(theaterId);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }
    @GetMapping("/getAllSeats")
    public ResponseEntity<Map<String , String>> getAllSeats(@RequestParam String screenId){
        Map<String , String > map = theaterService.getAllSeats(screenId);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }
}
