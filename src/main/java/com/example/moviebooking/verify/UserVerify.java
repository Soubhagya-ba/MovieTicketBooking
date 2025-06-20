package com.example.moviebooking.verify;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class UserVerify {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpToGmail(String toEmail, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(toEmail);
        helper.setSubject("Email Verification For User Registration");
        String htmlContent = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);'>" +
                "<h2 style='color: #2c3e50;'>OTP Verification</h2>" +
                "<p style='font-size: 16px; color: #333;'>Hello,</p>" +
                "<p style='font-size: 16px; color: #333;'>Thank you for using our service. Please use the following One-Time Password (OTP) to complete your verification. This OTP is valid for <strong>10 minutes</strong>.</p>" +
                "<div style='margin: 20px 0; text-align: center;'>" +
                "<span style='font-size: 28px; font-weight: bold; color: #ffffff; background-color: #3498db; padding: 10px 20px; border-radius: 6px; letter-spacing: 4px; display: inline-block;'>" + otp + "</span>" +
                "</div>" +
                "<p style='font-size: 14px; color: #888;'>Best regards,<br> Book My Ticket</p>" +
                "</div>";

        helper.setText(htmlContent, true);
        mailSender.send(message);
    }
}
