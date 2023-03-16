package com.socmed.socmed.modules.user;


import com.socmed.socmed.modules.auth.AuthenticationUpdateRequest;
import com.socmed.socmed.modules.post.Post;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/profile")

public record UserController(

        UserRepository userRepository
) {

    @PutMapping
    public ResponseEntity<?> UpdateUser(@RequestBody AuthenticationUpdateRequest request) {
        return ok("User Updated");
    }




}
