package com.socmed.socmed.post;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
@CrossOrigin(origins = "*")
public record PostController(
        PostService postService) {



    @GetMapping
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("yes");
    }
}

