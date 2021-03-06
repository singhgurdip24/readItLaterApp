package com.codesingh.readitlaterapp.controller;

import com.codesingh.readitlaterapp.exception.AppException;
import com.codesingh.readitlaterapp.model.Role;
import com.codesingh.readitlaterapp.model.RoleName;
import com.codesingh.readitlaterapp.model.User;
import com.codesingh.readitlaterapp.payload.ApiResponse;
import com.codesingh.readitlaterapp.payload.JwtAuthenticationResponse;
import com.codesingh.readitlaterapp.payload.LoginRequest;
import com.codesingh.readitlaterapp.payload.SignUpRequest;
import com.codesingh.readitlaterapp.repository.RoleRepository;
import com.codesingh.readitlaterapp.repository.UserRepository;
import com.codesingh.readitlaterapp.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenProvider tokenProvider;

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
    if(userRepository.existsByUsername(signUpRequest.getUsername())){
      return new ResponseEntity(new ApiResponse(false,"Username is not available!"),
        HttpStatus.BAD_REQUEST);
    }

    if(userRepository.existsByEmail(signUpRequest.getEmail())){
      return new ResponseEntity(new ApiResponse(false,"Email already exists"),
        HttpStatus.BAD_REQUEST);
    }

    User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
      signUpRequest.getEmail(), signUpRequest.getPassword());

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(()-> new AppException("Role not set"));

    user.setRoles(Collections.singleton(userRole));

    User result = userRepository.save(user);

    URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
      .buildAndExpand(result.getUsername()).toUri();

    return ResponseEntity.created(location).body(new ApiResponse(true, "User Registered Successfully"));
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        loginRequest.getUserOrEmail(),loginRequest.getPassword()
      )
    );

//  SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);

    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }
}
