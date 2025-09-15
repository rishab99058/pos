package com.vermau2k01053.pos_System.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vermau2k01053.pos_System.model.UserModel;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse extends BaseResponse {
    @JsonProperty("user_details")
    private UserModel  user;
    @JsonProperty("user_list")
    private List<UserModel> userList;
}
