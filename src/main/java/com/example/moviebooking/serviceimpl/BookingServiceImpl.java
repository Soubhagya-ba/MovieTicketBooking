package com.example.moviebooking.serviceimpl;

import com.example.moviebooking.dto.mapper.BookingMapper;
import com.example.moviebooking.dto.request.BookingRequest;
import com.example.moviebooking.dto.response.BookingResponse;
import com.example.moviebooking.entity.Booking;
import com.example.moviebooking.entity.Show;
import com.example.moviebooking.entity.ShowSeat;
import com.example.moviebooking.entity.UserDetails;
import com.example.moviebooking.enums.BookingStatus;
import com.example.moviebooking.exception.*;
import com.example.moviebooking.feature.GeneratePdf;
import com.example.moviebooking.feature.QrCodeGenerator;
import com.example.moviebooking.repository.BookingRepository;
import com.example.moviebooking.repository.MovieShowRepository;
import com.example.moviebooking.repository.ShowSeatRepository;
import com.example.moviebooking.repository.UserDetailsRepository;
import com.example.moviebooking.security.AuthUtilities;
import com.example.moviebooking.service.BookingService;
import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private UserDetailsRepository userDetailsRepository;
    private MovieShowRepository movieShowRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private GeneratePdf generatePdf;
    private EmailServiceImpl emailService;

    @Override
    public BookingResponse pendingBooking(BookingRequest request) {
        Show show = movieShowRepository.findById(request.showId()).orElseThrow(()->new MovieShowNotFoundException("Invalid Show Id!!"));
        List<ShowSeat> showSeats = showSeatRepository.findAllById(request.seatIds());
        lockSeat(request.seatIds());
        Booking booking = BookingMapper.toEntity(request,new Booking(),getUserId());
        booking.setShowSeats(showSeats);
        booking.setShow(show);
        booking = bookingRepository.save(booking);
        return BookingMapper.toResponse(booking);
    }

    @Override
    public String confirmBooking(String bookingId) {
        UserDetails userDetails = userDetailsRepository.findById(getUserId()).orElseThrow();
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new InvalidBookingIdException("Booking Id Not Found!!"));
        booking.setUpdateAt(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.CONFIRMED);

       List<ShowSeat> showSeats = booking.getShowSeats();
       for (ShowSeat ss : showSeats){
           ss.setBooked(true);
           ss.setLocked(false);
           ss.setLockedBy(null);
           ss.setLockedAt(null);
       }
       showSeatRepository.saveAll(showSeats);
       bookingRepository.save(booking);
        byte[] pdfBytes = generatePdf.generateConfirmBookingPdf(bookingId,booking.getShow().getId());
        try {
            emailService.sendEmailPdf(userDetails.getEmail(),pdfBytes);
            return "Booking Successful !! Check Your Registered Mail Id....";
        } catch (MessagingException e) {
            throw new MailSentException("Failed while send the mail");
        }
    }

    @Override
    public String cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new InvalidBookingIdException("Booking Id Not Found"));
        booking.setUpdateAt(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.CANCELLED);

        List<ShowSeat> showSeats = booking.getShowSeats();
        for (ShowSeat seat : showSeats){
            seat.setBooked(false);
            seat.setLocked(false);
            seat.setLockedBy(null);
            seat.setLockedAt(null);
            showSeats.add(seat);
        }
        showSeatRepository.saveAll(showSeats);
        bookingRepository.save(booking);
        return "Your Booking Has Been Canceled...";
    }

    @Override
    public List<String> getAvailableSeat(String showId) {
       List<ShowSeat> showSeats = showSeatRepository.findByShow_IdAndIsBookedFalse(showId);
       List<String> seats = new ArrayList<>();
       for (ShowSeat seat: showSeats){
           seats.add(seat.getId());
       }
       return seats;
    }

    private void lockSeat(List<String> seatId){
        for (String s : seatId){
            ShowSeat showSeat = showSeatRepository.findById(s).orElseThrow();
            if (showSeat.isBooked())
                throw new IllegalSeatException("Seat Already Booked");
            if (showSeat.isLocked())
                throw new IllegalSeatException("Seat is Temporally Locked");
            showSeat.setLocked(true);
            showSeat.setLockedBy(getUserId());
            showSeat.setLockedAt(System.currentTimeMillis());
            showSeatRepository.save(showSeat);
        }
    }
    private String getUserId(){
        return AuthUtilities.getUserName().orElseThrow(()->new UserNotLoggedInException("User Not Logged In!!"));
    }
}
