package com.example.moviebooking.serviceimpl;

import com.example.moviebooking.dto.mapper.UserDetailsMapper;
import com.example.moviebooking.dto.request.UserDetailsRequest;
import com.example.moviebooking.dto.request.UserLoginRequest;
import com.example.moviebooking.dto.response.UserDetailsResponse;
import com.example.moviebooking.entity.TheaterOwner;
import com.example.moviebooking.entity.User;
import com.example.moviebooking.entity.UserDetails;
import com.example.moviebooking.entity.VerifyUser;
import com.example.moviebooking.exception.*;
import com.example.moviebooking.repository.UserDetailsRepository;
import com.example.moviebooking.repository.VerifyUserRepository;
import com.example.moviebooking.service.UserDetailsService;
import com.example.moviebooking.verify.UserVerify;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;
    private UserDetailsMapper userDetailsMapper;
    private UserVerify verify;
    private VerifyUserRepository verifyUserRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public String login(UserLoginRequest request, HttpServletRequest httpRequest) {
        UserDetails userDetails = userDetailsRepository.findByEmail(request.email()).orElseThrow(() -> new InvalidEmailException("Invalid Email Id"));
        if (passwordEncoder.matches(request.password(), userDetails.getPassword())) {
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUserId(),null,authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
            httpRequest.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT",SecurityContextHolder.getContext());
            return "Login Success";
        } else throw new InvalidCredentialException("Invalid Email or Password");
    }

    @Override
    public String verifyEmail(String email) {
        if (verifyUserRepository.findByEmail(email).isPresent()){
            verifyUserRepository.deleteByEmail(email);
        }
        int otp = generateOtp();
        VerifyUser user = new VerifyUser();
        user.setEmail(email);
        user.setOtp(otp);
        user.setExpireAt(LocalDateTime.now().plusMinutes(10));
        verifyUserRepository.save(user);
        try {
            verify.sendOtpToGmail(email, String.valueOf(otp));
            return "Mail Successfully Sent To " + email;
        } catch (MessagingException e) {
            throw new MailSentException("Failed While Sending The Mail");
        }
    }

    @Transactional
    @Override
    public UserDetailsResponse registerUser(UserDetailsRequest request, int otp) {

        VerifyUser user = verifyUserRepository.findByEmail(request.email()).orElseThrow(() -> new InvalidEmailException("Enter Your Correct Email..That You Entered While Sending Otp!!!"));
        if (LocalDateTime.now().isBefore(user.getExpireAt())) {
            if (otp == user.getOtp()) {
                UserDetails userDetails = switch (request.userRole()) {
                    case USER -> userDetailsMapper.toEntity(request, new User());
                    case THEATER_OWNER -> userDetailsMapper.toEntity(request, new TheaterOwner());
                    default -> throw new UnSupportedUserRoleException("The UserRole Is Not Supported");
                };
                String password = passwordEncoder.encode(userDetails.getPassword());
                userDetails.setPassword(password);
                userDetailsRepository.save(userDetails);
                verifyUserRepository.delete(user);
                return userDetailsMapper.toResponse(userDetails);
            } else throw new OtpException("Please Enter Correct Otp");
        } else {
            verifyUserRepository.delete(user);
            throw new OtpException("Otp Is Expire... Enter email to Receive Otp");
        }
    }


    private int generateOtp() {
        return 100000 + (int) (Math.random() * 900000);
    }
}
