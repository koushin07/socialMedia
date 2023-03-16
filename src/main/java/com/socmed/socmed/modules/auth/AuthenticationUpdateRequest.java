package com.socmed.socmed.modules.auth;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationUpdateRequest {


    @NotEmpty(message = "please don't leave username empty")
    private String username;
    @NotEmpty(message = "Please input your password")
    private String password;
    @NotEmpty(message = "Please confirm your new password")
    private String confirm_password;

}
