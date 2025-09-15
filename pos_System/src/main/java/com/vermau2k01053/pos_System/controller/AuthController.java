package com.vermau2k01053.pos_System.controller;

import com.vermau2k01053.pos_System.request.UserRequest;
import com.vermau2k01053.pos_System.response.AuthResponse;
import com.vermau2k01053.pos_System.service.api.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign_up")
    public AuthResponse signUp(@RequestBody UserRequest userRequest) {
        return authService.registerUser(userRequest);
    }

    @PostMapping("/log_in")
    public AuthResponse logIn(@RequestBody UserRequest userRequest) {
        return authService.loginUser(userRequest);
    }
}
