package com.vermau2k01053.pos_System.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vermau2k01053.pos_System.model.UserModel;
import lombok.Data;

@Data
public class AuthResponse extends BaseResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("jwt_token")
    private String jwt;
    @JsonProperty("usr_details")
    private UserModel user;
}
