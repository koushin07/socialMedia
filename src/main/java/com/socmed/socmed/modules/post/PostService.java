package com.socmed.socmed.modules.post;

import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.modules.profile.   ProfileDTO;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserMapper;
import com.socmed.socmed.modules.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public record PostService(
        PostRepository postRepository,
        UserRepository userRepository
) {

    public List<Post> posts(){
        return postRepository.findAll();
    }

    public List<Post> postOfUser(Long userId) {

        return postRepository.findAllByUserId(userId);
    }

    public PostResponse createPost(PostCreationRequest request) throws ResourceNotFoundException {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("no user found"));
       Post post =   postRepository.save(Post.builder()
               .user(user)
               .postContent(request.getPostContent())
               .build());
        PostResponse response = PostMapper.INSTANCE.createResponse(post);
        response.setUserDTO(
                UserMapper.INSTANCE.authenticateMapper(user)
        );

        return response;
    }

    public Post updatePost(Long id, PostCreationRequest request) throws ResourceNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post is not found"));

        post.setPostContent(request.getPostContent());
        return postRepository.save(post);
    }
}
