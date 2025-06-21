package com.example.moviebooking.entity;

import com.example.moviebooking.enums.ScreenType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "screen")
public class Screen {

    @OneToMany(mappedBy = "screen")
    private Set<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToMany(mappedBy = "screen")
    private List<Show> shows;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "screenId", updatable = false, nullable = false)
    private String screenId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "screen_type", updatable = false, nullable = false)
    private ScreenType screenType;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "no_of_rows", nullable = false)
    private int noOfRows;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;
}
