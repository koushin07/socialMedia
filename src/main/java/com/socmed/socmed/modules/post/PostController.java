package com.socmed.socmed.modules.post;


import com.socmed.socmed.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/post")
@CrossOrigin(origins = "*")
@Slf4j
public record PostController(
        PostService postService
) {

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, List<Post>>> PostOfUser(@PathVariable("id") Long userId) {
        Map<String, List<Post>> result = new HashMap<>();
        result.put("posts", postService.postOfUser(userId));
        return ok(result);
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody  PostCreationRequest request) throws ResourceNotFoundException {
        log.info("creating new post user id "+request.getUserId() );

        return created(URI.create("")).body(postService.createPost(request));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id ,@RequestBody PostCreationRequest request) throws ResourceNotFoundException {

        return ok(postService.updatePost(id, request));
    }
}

