package com.example.ecommerceapp.Controller;

import com.example.ecommerceapp.Controller.response.AuthResponse;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.Exceptions.UserException;
import com.example.ecommerceapp.Repository.UserRepository;
import com.example.ecommerceapp.Service.implimentations.UserServiceImp;
import com.example.ecommerceapp.config.JwtProvider;
import com.example.ecommerceapp.requests.AddUserRequest;
import com.example.ecommerceapp.requests.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImp userServiceImp;

    public AuthController(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder, UserServiceImp userServiceImp) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody AddUserRequest user) throws UserException {


        if (userRepository.getUserByEmail(user.getEmail()) != null) {
            throw new UserException("Email is already in use.");
        }
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setAddresses(user.getAddress());
        newUser.setRole(user.getRole());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now());
        userRepository.save(newUser);

        Authentication authentication = authenticate(newUser.getEmail(), newUser.getPassword());
        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new AuthResponse(token, "Signup successful"));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticate(request.getEmail(), request.getPassword());
            String token = jwtProvider.generateToken(authentication);
            return ResponseEntity.ok(new AuthResponse(token, "Signin successful"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, "Invalid email or password"));
        }
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = userServiceImp.loadUserByEmail(email);

        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
