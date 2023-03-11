package com.socmed.socmed.post;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record PostService(PostRepository postRepository) {

    public List<Post> posts(){
        return postRepository.findAll();
    }
}
