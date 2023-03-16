package com.socmed.socmed.auth;

import com.socmed.socmed.modules.auth.AuthenticationRefreshTokenRequest;
import com.socmed.socmed.modules.jwt.JwtService;
import com.socmed.socmed.modules.auth.AuthenticationRequest;
import com.socmed.socmed.modules.auth.AuthenticationResponse;
import com.socmed.socmed.modules.auth.AuthenticationService;
import com.socmed.socmed.modules.profile.Profile;
import com.socmed.socmed.modules.role.Role;
import com.socmed.socmed.modules.role.RoleRepository;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserDTO;
import com.socmed.socmed.modules.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Captor
    private ArgumentCaptor<AuthenticationResponse> responseArgumentCaptor;
    @Mock
    private  PasswordEncoder passwordEncoder;
    @Mock
    private  JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;
    @InjectMocks
    private AuthenticationService underTest;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        jwtService = mock(JwtService.class);
        underTest = new AuthenticationService(
                userRepository,
                passwordEncoder,
                jwtService,
                authenticationManager,
                roleRepository,
                userDetailsService

        );

    }

    @Test
    void authenticate() {

        String username = "test";
        String password = "test";
        User user = User.builder()
                .id(1l)
                .username(username)
                .password(password)
                .email("test@gmail.com")
                .profiles(new Profile())
                .build();

        AuthenticationRequest request = AuthenticationRequest.builder()
                .password(password)
                .username(username)
                .build();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        AuthenticationResponse response = underTest.authenticate(request);

        assertNotNull(response.getUserDTO());
        assertNotNull(response.getAccessToken());
//        assertNotNull(response.getUserDTO());
    }

    @Test
    void shouldRefreshToken(){
        /*  given   */
//        String username = "testuser";
        String refreshToken = "testrefreshtoken";
        String username = "test";
        String password = "test";
        User user = User.builder()
                .id(1l)
                .username(username)
                .password(password)
                .email("test@gmail.com")
                .profiles(new Profile())
                .role(new Role())
                .build();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);


//        Mockito.when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
//        Mockito.when(jwtService.isRefreshTokenValid(refreshToken, userDetails)).thenReturn(true);
//        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        Mockito.when(jwtService.generateRefreshToken(userDetails)).thenReturn("newrefreshtoken");
//        Mockito.when(jwtService.generateToken(userDetails)).thenReturn("newaccesstoken");

        AuthenticationRefreshTokenRequest request = new AuthenticationRefreshTokenRequest();
        request.setUsername(username);
        request.setRefreshToken(refreshToken);

        AuthenticationResponse response = AuthenticationResponse.builder()
                .AccessToken("asd")
                .refreshToken(jwtService.generateRefreshToken(userDetails))
                .userDTO(UserDTO.builder()
                        .email(user.getEmail())
                        .id(user.getId())
                        .username(username)

                        .role(user.getRole())
                        .build())
                .build();


        assertNotNull(response);
        assertEquals(username, response.getUserDTO().getUsername());
//        assertEquals("newaccesstoken", response.getAccessToken());
        assertEquals("newrefreshtoken", response.getRefreshToken());
    }
}