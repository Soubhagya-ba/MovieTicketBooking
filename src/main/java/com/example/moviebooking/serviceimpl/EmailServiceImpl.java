package com.example.moviebooking.serviceimpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailPdf(String toEmail , byte[] pdfBytes) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setTo(toEmail);
        helper.setSubject("Booking Information");
        helper.setText("This is Your Booking Related Information");
        ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfBytes,"application/pdf");
        helper.addAttachment("booking_details.pdf",dataSource);
        mailSender.send(message);
    }
}
