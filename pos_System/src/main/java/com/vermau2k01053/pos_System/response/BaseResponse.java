package com.vermau2k01053.pos_System.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("status_code")
    private HttpStatus statusCode;
    @JsonProperty("success")
    private boolean success;
}
