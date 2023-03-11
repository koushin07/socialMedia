package com.socmed.socmed.auth;

import com.socmed.socmed.jwt.JwtService;
import com.socmed.socmed.profile.Profile;
import com.socmed.socmed.role.RoleRepository;
import com.socmed.socmed.user.User;
import com.socmed.socmed.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
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
                roleRepository
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
}