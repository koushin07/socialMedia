package com.socmed.socmed.auth;

import com.socmed.socmed.modules.auth.AuthenticationController;
import com.socmed.socmed.modules.auth.AuthenticationRequest;
import com.socmed.socmed.modules.auth.AuthenticationResponse;
import com.socmed.socmed.modules.auth.AuthenticationService;
import com.socmed.socmed.modules.profile.Profile;
import com.socmed.socmed.modules.profile.ProfilePicture;
import com.socmed.socmed.modules.role.Role;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void authenticate() throws Exception {
        // Arrange
        String username = "testuser";
        String password = "testpassword";
        String jwtToken = "testjwt";
        Profile profile = Profile.builder().id(1l).user(new User()).firstName("miko").lastName("canares").middleName("castillo").suffix("").bio("aw").profilePicture(new ProfilePicture()).build();
        Role role = Role.builder().name("admin").build();
        User user = User.builder().id(1L).email("test@gmail.com").username("test").password("test").role(role).profiles(profile).build();

        UserDTO userDTO = UserDTO.builder().id(1L).email("test@gmail.com").username("test").role(role).build();

        AuthenticationRequest request = new AuthenticationRequest(username, password);
        AuthenticationResponse expectedResponse = new AuthenticationResponse(jwtToken, null, userDTO);

        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(expectedResponse);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}");

        // Act
        ResultActions result = mockMvc.perform(requestBuilder);

        // Assert
        result.andExpect(MockMvcResultMatchers.status().isOk()).andExpect((ResultMatcher) jsonPath("$.AccessToken").value(jwtToken)).andExpect((ResultMatcher) jsonPath("$.refreshToken").doesNotExist()).andExpect((ResultMatcher) jsonPath("$.userDTO.username").value(userDTO.getUsername())).andExpect((ResultMatcher) jsonPath("$.userDTO.email").value(userDTO.getEmail()));

    }
}