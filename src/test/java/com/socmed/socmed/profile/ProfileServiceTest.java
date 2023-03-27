package com.socmed.socmed.profile;

import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.modules.profile.*;
import com.socmed.socmed.modules.role.RoleRepository;
import com.socmed.socmed.modules.user.UserRepository;
import com.socmed.socmed.modules.role.Role;
import com.socmed.socmed.modules.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;
    @InjectMocks
    private ProfileService underTest;
    @Mock
    private ProfileMapper mapper;
    @Captor
    private ArgumentCaptor<Profile> profileArgumentCaptor;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);

        underTest = new ProfileService(profileRepository, userRepository);
    }
    @Test
    void shouldCreateProfile() throws ResourceNotFoundException {
        ProfileCreationRequest request = ProfileCreationRequest.builder()
                .userId(1l)
                .firstName("miko")
                .middleName("castillo")
                .suffix("")
                .lastName("canares")
                .profilePicURL("")
                .bio("")
                .build();
        User user = User.builder()
                .id(1L)
                .username("wewet")
                .role(new Role())
                .email("canares@gmail.com")
                .password("123d")
                .build();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
//        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Profile profile = underTest.createProfile(request);
        profileArgumentCaptor = ArgumentCaptor.forClass(Profile.class);

        verify(profileRepository, times(1)).save(profileArgumentCaptor.capture());
        Profile captured = profileArgumentCaptor.getValue();


        assertAll("values",
                ()->  assertEquals(user.getUsername(), captured.getUser().getUsername()),
                ()-> assertNotNull(user),
                ()-> assertNotNull(captured.getUser().getUsername()),
                ()->  assertEquals(request.getFirstName(), captured.getFirstName())
        );
        System.out.printf("\033[3mrequest \033[0m and" +
                " \033[3mcaptured \033[0m " + "is  \033[1m\033[4mnot null\033[0m\033[0m " +
                        "and has the same \033[1m\033[4mvalue\033[0m\033[0m"

                );

    }

    @Test
    void ShouldUpdateProfile() throws ResourceNotFoundException {

        /*given*/
        Role role = Role.builder()
                .id(2L)
                .name("admin")
                .build();
        User user = User.builder()
                .id(1L)
                .role(role)
                .email("canares@gmail.com")
                .password("123d")
                .build();
        ProfileDTO profileDTO = ProfileDTO.builder()
                .user(user)
                .firstName("nikko")
                .lastName("canaressss")
                .middleName("castillo")
                .suffix("")
                .bio("aw")
                .profilePicURL("none")
                .build();
        Long newprofileID = 1l;
        Profile newprofile = Profile.builder()
                .id(newprofileID)
                .user(user)
                .firstName("miko")
                .lastName("canares")
                .middleName("castillo")
                .suffix("")
                .bio("aw")
                .profilePicURL("none")
                .build();
        /*when*/
//        when(roleRepository.findById(role.getId())).thenReturn(Optional.of(role));
//        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        when(profileRepository.findById(1L)).thenReturn(Optional.of(newprofile));

//        when(profileRepository.save(newprofile)).thenReturn(newprofile);
        System.out.println("this is the newCreate "+newprofile);
        Profile updatedProfile = underTest.updateProfile(1L, profileDTO);

        profileArgumentCaptor = ArgumentCaptor.forClass(Profile.class);
        verify(profileRepository, times(1)).save(profileArgumentCaptor.capture());

        Profile captured = profileArgumentCaptor.getValue();
        assertEquals(captured.getFirstName(), profileDTO.getFirstName());
//        assertNotEquals(captured, newprofile);
    }
}