package com.socmed.socmed.modules.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    @NotEmpty(message = "please dont leave username empty")
    private String username;
    @Email(message = "your email is invalid")
    private String email;
    private String password;
    private String role;
}
