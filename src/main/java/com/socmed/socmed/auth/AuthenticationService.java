package com.socmed.socmed.auth;

import com.socmed.socmed.jwt.JwtService;
import com.socmed.socmed.role.Role;
import com.socmed.socmed.role.RoleRepository;
import com.socmed.socmed.user.User;
import com.socmed.socmed.user.UserDTO;
import com.socmed.socmed.user.UserMapper;
import com.socmed.socmed.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationResponse register(RegisterRequest request) {
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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new UsernameNotFoundException("username not found"));
        log.info("authenticated user " + user.getUsername());
       UserDTO userDTO = UserMapper.INSTANCE.authenticateMapper(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().AccessToken(jwtToken).userDTO(userDTO).build();

    }
}
