package com.socmed.socmed.modules.follow;

import com.socmed.socmed.modules.role.Role;
import com.socmed.socmed.modules.role.RoleRepository;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FollowRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FollowRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
//    void findMutualFollowersByUserIdAndFollowerId() {
//        Role role = roleRepository.save(Role.builder().name("admin").build());
//        User user = userRepository.save(User.builder()
//                .username("miko")
//                .email("miko@gmail.com")
//                .password("admin")
//                .role(role)
//                .build());
//        User follower = userRepository.save(User.builder()
//                .username("miko")
//                .email("miko@gmail.com")
//                .password("admin")
//                .role(role)
//                .build());
//
//        Follow follow1 = underTest.save(Follow.builder().follower(follower).user(user).build());
//      FollowerId id =  FollowerId.builder().follower(follower.getId()).user(user.getId()).build();
//
//      List<MutualFollowersDTO> mutaul = underTest.findMutualFollowersByUserIdAndFollowerId(user.getId(), follower.getId());
//        assertEquals(0, mutaul.size());
//        System.out.println("this is the persist data " );
//    }
}