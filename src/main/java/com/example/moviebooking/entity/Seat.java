package com.example.moviebooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "seat")
public class Seat {

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "seat_id",nullable = false,updatable = false)
    private String seatId;

    @Column(name = "seatNo",nullable = false)
    private String seatNo;

    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
}
