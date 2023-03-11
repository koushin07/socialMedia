package com.socmed.socmed.user;

import com.socmed.socmed.role.Role;
import com.socmed.socmed.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {


    @Autowired
    private UserRepository underTest;
    @Autowired
    private RoleRepository roleRepository;
    @Test
    void findByUsername() {
        /*given*/
        Role role = roleRepository.saveAndFlush(Role.builder().name("admin").build());
        String username="miko";
        User user = User.builder()
                .username(username)
                .role(role)
                .email("miko@gmail.com")
                .password("1234")
                .build();

        underTest.save(user);
        /*when*/
        Optional<User> expected = underTest.findByUsername(username);

        /*then*/
        assertEquals(expected.get(), user);
    }
}