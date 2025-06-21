package com.example.moviebooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "movie_show")
public class Show {

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "show_id",nullable = false,updatable = false)
    private String showId;

    @Column(name = "starts_at",nullable = false)
    private LocalDateTime startsAt;

    @Column(name = "ends_at",nullable = false)
    private LocalDateTime endsAt;

    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at",nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "created_by",nullable = false)
    private String createdBy;

}
