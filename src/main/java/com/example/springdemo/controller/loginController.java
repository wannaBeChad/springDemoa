package com.example.springdemo.controller;

import com.example.springdemo.dto.LoginRequestDto;
import com.example.springdemo.dto.LoginResponseDto;
import com.example.springdemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {
    private final UserService userService;

    public loginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto responseDto;
        if (userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())) {
            // generate a JWT token or other mechanism for authentication
            // ...
             responseDto= new LoginResponseDto("login success!",null);
            return ResponseEntity.ok().body(responseDto);
        } else {
            responseDto = new LoginResponseDto(null,"Login failure!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
        }
    }

}
