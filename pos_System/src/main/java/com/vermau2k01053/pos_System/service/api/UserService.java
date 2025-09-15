package com.vermau2k01053.pos_System.service.api;

import com.vermau2k01053.pos_System.request.UserRequest;
import com.vermau2k01053.pos_System.response.UserResponse;

public interface UserService {

    UserResponse getUserByJwtToken(String jwtToken);
    UserResponse getCurrentUser();
    UserResponse getUserByEmail(UserRequest userRequest);
    UserResponse getUserById(UserRequest userRequest);
    UserResponse getUserListing();
}
