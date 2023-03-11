package com.socmed.socmed.auth;

import com.socmed.socmed.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String AccessToken;

    private String refreshToken;

    private UserDTO userDTO;
}
