package com.socmed.socmed.modules.follow;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/follows")
@CrossOrigin(origins = "*")
public record FollowController( FollowService followService) {


    /**
     *
     * @param followerId selected user ID
     * @param userId auth user ID
     * @return String confirmation
     */
    @PostMapping("/{followerId}/user/{userId}")
    public ResponseEntity<String> followUser(@PathVariable("followerId") Long followerId, @PathVariable("userId") Long userId){
        return created(URI.create("")).body(followService.createFollow(followerId, userId));
    }
    /**
     * @ TODO return value
      * @param followerId selected user ID
     * @param userId auth user ID
     * @return return selected user followers and common followers with auth user
     */
    @GetMapping("/{followerId}/user/{userId}")
    public ResponseEntity<List<MutualFollowersDTO>> commonFollowerResponse(@PathVariable("followerId") Long followerId, @PathVariable("userId") Long userId){
    return ok(followService.findAllFollowers(userId, followerId));
    }
}
