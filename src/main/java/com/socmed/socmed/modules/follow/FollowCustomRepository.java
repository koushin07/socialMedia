package com.socmed.socmed.modules.follow;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FollowCustomRepository {
     List<MutualFollowersDTO> findMutualFollowersByUserIdAndFollowerId(Long userId, Long followerId);
}
