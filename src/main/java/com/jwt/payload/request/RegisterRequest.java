package com.jwt.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
}
