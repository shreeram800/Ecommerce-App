package com.example.ecommerceapp.Controller;


import com.example.ecommerceapp.Controller.response.AuthResponse;
import com.example.ecommerceapp.Entity.User;
import com.example.ecommerceapp.Exceptions.UserException;
import com.example.ecommerceapp.Repository.UserRepository;
import com.example.ecommerceapp.Service.implimentations.UserServiceImp;
import com.example.ecommerceapp.config.JwtProvider;
import com.example.ecommerceapp.requests.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private JwtProvider provider;
    private PasswordEncoder passwordEncoder;

    private UserServiceImp userServiceImp;
    public AuthController(UserRepository repository, JwtProvider provider, PasswordEncoder passwordEncoder, UserServiceImp userServiceImp) {
        this.userRepository = repository;
        this.provider = provider;
        this.passwordEncoder = passwordEncoder;
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        // Check if the email already exists
        String email = user.getEmail();
        User existingUser = userRepository.getUserByEmail(email);

        if (existingUser != null) {
            throw new UserException("Email already used in another account.");
        }

        // Create a new user instance
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
        newUser.setCreatedAt(LocalDateTime.now());

        // Save the user to the database
        User savedUser = userRepository.save(newUser);

        // Authenticate the user and generate a JWT token
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),
                savedUser.getPassword()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = provider.generateToken(authentication);

        // Prepare the response
        AuthResponse authResponse = new AuthResponse(token, "SignUp Success");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON) // Ensure JSON response
                .body(authResponse);
    }


    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> loginUserHandler(@Valid @RequestBody LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        try {
            // Authenticate the user
            Authentication authentication = authenticate(email, password);

            // Set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate the JWT token
            String token = provider.generateToken(authentication);

            // Prepare the response
            AuthResponse authResponse = new AuthResponse(token, "SignIn Success");

            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthResponse(null, "Invalid email or password"), HttpStatus.UNAUTHORIZED);
        }
    }


    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = userServiceImp.loadUserByEmail(userName);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
