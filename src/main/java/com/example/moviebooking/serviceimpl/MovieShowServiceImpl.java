package com.example.moviebooking.serviceimpl;

import com.example.moviebooking.dto.request.MovieShowRequest;
import com.example.moviebooking.entity.*;
import com.example.moviebooking.exception.InvalidTheaterIdException;
import com.example.moviebooking.exception.MovieNotFoundException;
import com.example.moviebooking.exception.ScreenNotFoundException;
import com.example.moviebooking.exception.TheaterOwnerNotLoginException;
import com.example.moviebooking.repository.*;
import com.example.moviebooking.security.AuthUtilities;
import com.example.moviebooking.service.MovieShowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class MovieShowServiceImpl implements MovieShowService {

    private TheaterRepository theaterRepository;
    private ScreenRepository screenRepository;
    private MovieRepository movieRepository;
    private MovieShowRepository movieShowRepository;
    private SeatRepository seatRepository;
    private ShowSeatRepository showSeatRepository;


    @Override
    public String addMovieShow(MovieShowRequest request) {
        String ownerId = AuthUtilities.getUserName().orElseThrow(()->new TheaterOwnerNotLoginException("Theater Owner Not Logged In !!"));
        Theater theater = theaterRepository.findById(request.theaterId()).orElseThrow(()->new InvalidTheaterIdException("Theater Not Found!!"));
        Screen screen = screenRepository.findById(request.screenId()).orElseThrow(()->new ScreenNotFoundException("Screen Not Found!!"));
        Movie movie = movieRepository.findById(request.movieId()).orElseThrow(()->new MovieNotFoundException("Movie Not Found!!"));

        Duration runtime = movie.getRuntime();
        LocalDateTime endsAt = request.startsAt().plus(runtime);

        Show show = new Show();
        show.setStartsAt(request.startsAt());
        show.setEndsAt(endsAt);
        show.setCreateAt(LocalDateTime.now());
        show.setUpdateAt(LocalDateTime.now());
        show.setCreatedBy(ownerId);
        show.setScreen(screen);
        show.setTheater(theater);
        show.setMovie(movie);
        movieShowRepository.save(show);

        List<Seat> seats = seatRepository.findByScreen(screen);
        List<ShowSeat> showSeats = new ArrayList<>();

        for (Seat seat : seats){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setBooked(false);
            showSeat.setLocked(false);
            showSeat.setLockedAt(null);
            showSeat.setLockedBy(null);
            showSeat.setSeat(seat);
            showSeat.setShow(show);
            showSeats.add(showSeat);
        }
        showSeatRepository.saveAll(showSeats);

        return "Show Created For "+theater.getName()+" -> "+screen.getName()+" -> "+movie.getTitle();
    }

    @Override
    public List<Show> findAllShows() {
        return List.of();
    }



}
