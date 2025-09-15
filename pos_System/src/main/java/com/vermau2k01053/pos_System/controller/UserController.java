package com.vermau2k01053.pos_System.controller;

import com.vermau2k01053.pos_System.request.UserRequest;
import com.vermau2k01053.pos_System.response.UserResponse;
import com.vermau2k01053.pos_System.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public UserResponse getUserProfile(
            @RequestHeader("Authorization") String jwt
    ){
        return userService.getUserByJwtToken(jwt);
    }

    @GetMapping("/get_user")
    public UserResponse getUserById(@RequestParam(required = true, name = "user_id") String id,
                                    @RequestHeader("Authorization") String jwt){
        UserRequest userRequest = new UserRequest();
        userRequest.setId(id);
        return userService.getUserById(userRequest);
    }
}
