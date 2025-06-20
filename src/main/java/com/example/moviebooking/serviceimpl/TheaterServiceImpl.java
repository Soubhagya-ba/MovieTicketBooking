package com.example.moviebooking.serviceimpl;

import com.example.moviebooking.dto.mapper.TheaterMapper;
import com.example.moviebooking.dto.request.ScreenRequest;
import com.example.moviebooking.dto.request.TheaterRequest;
import com.example.moviebooking.dto.response.ScreenResponse;
import com.example.moviebooking.dto.response.TheaterResponse;
import com.example.moviebooking.entity.*;
import com.example.moviebooking.exception.InvalidTheaterIdException;
import com.example.moviebooking.exception.ScreenNotFoundException;
import com.example.moviebooking.exception.UnAuthorizeException;
import com.example.moviebooking.exception.TheaterOwnerNotLoginException;
import com.example.moviebooking.repository.ScreenRepository;
import com.example.moviebooking.repository.SeatRepository;
import com.example.moviebooking.repository.TheaterRepository;
import com.example.moviebooking.repository.UserDetailsRepository;
import com.example.moviebooking.security.AuthUtilities;
import com.example.moviebooking.service.TheaterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.moviebooking.dto.mapper.ScreenMapper.*;


@Service
@AllArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private TheaterMapper theaterMapper;
    private TheaterRepository theaterRepository;
    private UserDetailsRepository userDetailsRepository;
    private ScreenRepository screenRepository;
    private SeatRepository seatRepository;

    @Transactional
    @Override
    public TheaterResponse createTheater(TheaterRequest request) {
        String theaterOwnerId = getUserId();
        UserDetails userDetails = userDetailsRepository.findById(theaterOwnerId).orElseThrow();
        if (userDetails instanceof TheaterOwner theaterOwner){
            Theater theater = theaterMapper.toEntity(request,new Theater());
            theater.setCreatedBy(theaterOwnerId);
            theater.setTheaterOwner(theaterOwner);
            theaterRepository.save(theater);
            return theaterMapper.toResponse(theater);
        }
        throw new UnAuthorizeException("Only Theater Owner Can Create Theater!!!");
    }

    @Override
    public Map<String, String> getAllTheater() {
        String ownerId = getUserId();
        UserDetails userDetails = userDetailsRepository.findById(ownerId).orElseThrow();
        Map<String , String> map = new HashMap<>();
        if (userDetails instanceof TheaterOwner theaterOwner){
            List<Theater> theaters = ((TheaterOwner) userDetails).getTheaters();
            for (Theater t : theaters){
                map.put(t.getTheaterId(),t.getName());
            }
        }
        return map;
    }

    @Transactional
    @Override
    public ScreenResponse addScreen(ScreenRequest request, String theaterId) {
        String theaterOwnerId = getUserId();
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(()->new InvalidTheaterIdException("Theater Not Exist!!"));
        Screen screen = toEntity(request,new Screen());
        screen.setCreatedBy(theaterOwnerId);
        screen.setTheater(theater);
        Set<Seat> seats = new HashSet<>(screen.getCapacity());
        char row = 'A';
        int col = 1;
        for (int i=0;i< screen.getCapacity();i++){
            Seat seat = new Seat();
            seat.setCreatedAt(LocalDateTime.now());
            seat.setScreen(screen);
            seat.setSeatNo(row+ String.valueOf(col++));
            if (col>10){
                col=1;
                row++;
            }
            seats.add(seat);
        }
        seatRepository.saveAll(seats);

        screen.setSeats(seats);
        screenRepository.save(screen);

        return toResponse(screen);
    }

    @Override
    public Map<String , String> getAllScreen(String theaterId){
        Set<Screen> screens = theaterRepository.findById(theaterId).map(theater->theater.getScreen()).orElseThrow(()->new InvalidTheaterIdException("Theater Not Found!!"));
        Map<String , String> screenMap = new HashMap<>();
        for (Screen s: screens){
            screenMap.put(s.getScreenId(),s.getScreenType().name());
        }
        return screenMap;
    }

    @Override
    public Map<String, String> getAllSeats(String screenId) {
        Set<Seat> seats = screenRepository.findById(screenId).map(screen -> screen.getSeats()).orElseThrow(()->new ScreenNotFoundException("Screen Not Found!!"));
        Map<String , String > seatMap = new HashMap<>();
        for (Seat s : seats){
            seatMap.put(s.getSeatId(),s.getSeatNo());
        }
        return seatMap;
    }

    private String getUserId(){
      return AuthUtilities.getUserName().orElseThrow(()->new TheaterOwnerNotLoginException("Theater Owner Not Login In!!"));
    }

}
