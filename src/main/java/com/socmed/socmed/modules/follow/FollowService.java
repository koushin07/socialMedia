package com.socmed.socmed.modules.follow;

import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public record FollowService(
        FollowRepository followRepository,
        UserRepository userRepository
) {

    /**
     * @Responsibility will get all the followers of user and common followers of that user
     * @param userId
     * @param followerId
     * @return
     */
    public List<MutualFollowersDTO> findAllFollowers(Long userId, Long followerId){
        return followRepository.findMutualFollowersByUserIdAndFollowerId(userId, followerId);
    }

    public void followUser(Long followeeId, Long followerId) {
        User followee = userRepository.findById(followeeId)
                .orElseThrow(() -> new ResourceNotFoundException("no user found"));
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("no user found"));

        followRepository.save(Follow.builder()
                .user(followee)
                .follower(follower)
                .build());
    }

    public String createFollow(Long followId, Long userId) {
        User follower  = userRepository.findById(followId)
                .orElseThrow(()-> new ResourceNotFoundException("cant follow since user didn't exist"));
        User user  = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("cant user"));

        Follow follow = Follow.builder()
                .follower(follower)
                .user(user)
                .build();
        followRepository.save(follow);
        return "you now follow user " + follower.getUsername();
    }
}
