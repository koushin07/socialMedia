package com.socmed.socmed.modules.auth;

import com.socmed.socmed.exception.EmailExistException;
import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.modules.jwt.BlackListRepository;
import com.socmed.socmed.modules.jwt.BlackListService;
import com.socmed.socmed.modules.jwt.JwtService;
import com.socmed.socmed.modules.role.Role;
import com.socmed.socmed.modules.role.RoleRepository;
import com.socmed.socmed.modules.user.*;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public record AuthenticationService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        AuthenticationManager authenticationManager,
        RoleRepository roleRepository,
        UserDetailsService userDetailsService,
        BlackListService blackListService

) {


    public AuthenticationResponse register(RegisterRequest request) {

        /*   checking if email exist   */
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailExistException("this email is already taken");
        }
        Role role = roleRepository.findByName(request.getRole());
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().AccessToken(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("authenticating.........");
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new ResourceNotFoundException("username not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        log.info("authenticated user " + user.getUsername());
        UserDTO userDTO = UserMapper.INSTANCE.authenticateMapper(user);
        String jwtToken = jwtService.generateToken(user);
        String refresher = jwtService.generateRefreshToken(user);
        log.info("this is the refresher " +refresher);
        return AuthenticationResponse.builder()
                .AccessToken(jwtToken)
                .refreshToken(refresher)
                .userDTO(userDTO).build();

    }

    public AuthenticationResponse refreshToken(AuthenticationRefreshTokenRequest request) {
        blackListService.blackListToken(request.getAccessToken());

        log.info("request value is \n " + request);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()->new ResourceNotFoundException("user is not found"));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        log.info(userDetails.getUsername());
        if (!jwtService.isRefreshTokenValid(request.getRefreshToken(), userDetails)) {
            throw new JwtException("access token not valid");
        }

        log.info("generating refresh token for user " + user.getUsername());
        return AuthenticationResponse.builder()
                .userDTO(UserMapper.INSTANCE.authenticateMapper(user))
                .refreshToken(jwtService.generateRefreshToken(userDetails))
                .AccessToken(jwtService.generateToken(userDetails))
                .build();
    }


}
