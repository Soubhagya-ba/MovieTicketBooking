package com.example.moviebooking.feature;

import com.example.moviebooking.entity.*;
import com.example.moviebooking.exception.UserNotLoggedInException;
import com.example.moviebooking.repository.BookingRepository;
import com.example.moviebooking.repository.MovieShowRepository;
import com.example.moviebooking.repository.TheaterRepository;
import com.example.moviebooking.repository.UserDetailsRepository;
import com.example.moviebooking.security.AuthUtilities;
import com.google.zxing.WriterException;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class GeneratePdf {

    private  UserDetailsRepository userDetailsRepository;
    private BookingRepository bookingRepository;
    private MovieShowRepository movieShowRepository;

    public  byte[] generateConfirmBookingPdf(String bookingId, String showId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        Show show = movieShowRepository.findById(showId).orElseThrow();
        UserDetails userDetails = userDetailsRepository.findById(AuthUtilities.getUserName().orElseThrow(()->new UserNotLoggedInException("User Not LoggIn Exception"))).orElseThrow();
        List<ShowSeat> seatList = booking.getShowSeats();
        StringBuilder seats = new StringBuilder();
        for (ShowSeat s : seatList){
            seats.append(s.getSeat().getSeatNo()+" ");
        }
        String content = String.format( """
               {
               "UserName":"%s"\n,
               "SeatNo":"%s" \n,
               "Amount":"%s"              
               }
               """,userDetails.getName(),seats,booking.getTotalAmount());
        byte[] qrBytes=null;
        try {
            qrBytes = QrCodeGenerator.generateQrCode(content,100,100);
        } catch (WriterException | IOException e) {

        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4.rotate());
        Document document = new Document(pdfDocument);
        document.add(new Paragraph("Booking Details").setBold().setTextAlignment(TextAlignment.CENTER).setFontSize(18).setMarginBottom(20));

        Table table = new Table(7);
        table.addCell("Booking Id");
        table.addCell("Booking Name");
        table.addCell("Booking Amount");
        table.addCell("Seat No");
        table.addCell("Theater Name & Screen Name");
        table.addCell("Movie Name");
        table.addCell("Starting Time - Ending Time");

        table.addCell(booking.getBookingId());
        table.addCell(userDetails.getName());
        table.addCell(String.valueOf(booking.getTotalAmount()));
        table.addCell(seats.toString());
        table.addCell(show.getTheater().getName()+" "+"&"+" "+show.getScreen().getName());
        table.addCell(show.getMovie().getTitle());
        table.addCell(String.valueOf(show.getStartsAt())+"  -  "+show.getEndsAt());
        document.add(table);


        Image qrImage = new Image(ImageDataFactory.create(qrBytes));
        qrImage.setAutoScale(true);
        qrImage.setMarginTop(25);
        qrImage.setTextAlignment(TextAlignment.CENTER);
        document.add(qrImage);
        document.close();
        return outputStream.toByteArray();
    }
}
