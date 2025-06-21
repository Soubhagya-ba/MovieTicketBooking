package com.example.moviebooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "theater")
public class Theater {

    @OneToMany(mappedBy = "theater")
    private Set<Screen> screen;

    @ManyToOne
    @JoinColumn(name = "theater_owner_id")
    private TheaterOwner theaterOwner;

    @OneToMany(mappedBy = "theater")
    private List<Show> shows;

    @OneToMany(mappedBy = "theater")
    private List<Feedback> feedbacks;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "theater_id",nullable = false,updatable = false)
    private String theaterId;

    @Column(name = "name",updatable = false)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "landmark",nullable = false)
    private String landmark;

    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by",nullable = false)
    private String createdBy;
}
