package com.vermau2k01053.pos_System.service.api;

import com.vermau2k01053.pos_System.request.UserRequest;
import com.vermau2k01053.pos_System.response.AuthResponse;

public interface AuthService {
    AuthResponse registerUser(UserRequest userRequest);
   AuthResponse loginUser(UserRequest userRequest);
//    AuthResponse forgotPassword(String email);
//    AuthResponse resetPassword(PasswordResetRequest request);
    AuthResponse logout(String jwtToken);
}

