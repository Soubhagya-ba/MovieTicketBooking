package com.example.moviebooking.entity;

import com.example.moviebooking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "booking")
public class Booking {

    @ManyToOne
    private Show show;

    @ManyToMany
    private List<ShowSeat> showSeats;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id",nullable = false,updatable = false)
    private String bookingId;

    @Column(name = "booking_status",nullable = false)
    private BookingStatus bookingStatus;

    @Column(name = "total_amount",nullable = false)
    private Double totalAmount;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at",nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "created_by",nullable = false)
    private String createdBy;
}
