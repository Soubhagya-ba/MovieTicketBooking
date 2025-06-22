package com.example.moviebooking.repository;

import com.example.moviebooking.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeat , String> {
    Optional<ShowSeat> findByIdAndSeat_SeatId(String id, String seatId);
    List<ShowSeat> findByIdAndSeat_SeatIdIn(String showId, List<String> seatIds);
    List<ShowSeat> findByIsLockedTrue();
    List<ShowSeat> findByShow_IdAndIsBookedFalse(String showId);
}
