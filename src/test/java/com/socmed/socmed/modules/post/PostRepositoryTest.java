package com.socmed.socmed.modules.post;

import com.socmed.socmed.modules.comment.Comment;
import com.socmed.socmed.modules.comment.CommentRepository;
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
class PostRepositoryTest {

    @Autowired
    private PostRepository underTest;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindAllPostWithComments(){

        Role role = roleRepository.save(Role.builder()
                .name("admin").build());
        User user = userRepository.save(User.builder()
                .username("admin")
                .email("canaresmiko3@gmail.com")
                .password("admin")
                .role(role).build());
        Post post = underTest.save(Post.builder()
                .postContent("this is post")
                .user(user).build());
        Comment comment = commentRepository.save(Comment.builder()
                .CommentContent("this is commend")
                .post(post)
                .user(user).build());


        List<Post> response = underTest.findAll();
        System.out.println( response);
        List<Comment> comments = response.get(0).getComments().stream().toList();
        assertAll(
                () -> assertEquals(post.getPostContent(), response.get(0).getPostContent()),
                () -> assertFalse(comments.isEmpty()),
                () -> assertEquals(comment.getCommentContent(), comments.get(0).getCommentContent())
        );

        }


}