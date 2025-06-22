package com.example.moviebooking.repository;

import com.example.moviebooking.entity.Screen;
import com.example.moviebooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat , String> {
    List<Seat> findByScreen(Screen screen);
}
