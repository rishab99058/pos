package com.vermau2k01053.pos_System.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vermau2k01053.pos_System.domain.UserRole;
import lombok.Data;

@Data
public class UserModel {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("role")
    private UserRole role;
}
