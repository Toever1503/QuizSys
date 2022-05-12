package com.jwt.payload.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;
//    private boolean rememberme;
}
