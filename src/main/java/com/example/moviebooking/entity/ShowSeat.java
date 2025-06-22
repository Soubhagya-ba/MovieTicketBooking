package com.example.moviebooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "show_seat")
public class ShowSeat {

    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @ManyToMany(mappedBy = "showSeats")
    private List<Booking> bookingList;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "is_locked")
    private boolean isLocked;

    @Column(name = "locked_by")
    private String lockedBy;

    @Column(name = "locked_at")
    private Long lockedAt;

    @Column(name = "is_booked")
    private boolean isBooked;

}
