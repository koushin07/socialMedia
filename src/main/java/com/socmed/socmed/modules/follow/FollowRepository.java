package com.socmed.socmed.modules.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository  extends JpaRepository<Follow, FollowerId>, FollowCustomRepository {

    @Query(value = "SELECT f FROM Follow f")
    Follow findByFollowerId(Long id);

//    @Query(value = "SELECT pr.first_name, pr.last_name, pr.middle_name, pr.suffix,\n" +
//            "       CASE \n" +
//            "         WHEN f.user_id = 1 THEN COUNT(*) \n" +
//            "         ELSE 0 \n" +
//            "       END AS mutual\n" +
//            "FROM follows f \n" +
//            "JOIN users follower ON follower.id = f.follower_id \n" +
//            "JOIN profiles pr ON pr.user_id = follower.id \n" +
//            "WHERE follower.id = :followerId AND f.user_id = :userId \n" +
//            "GROUP BY pr.first_name,  pr.last_name, pr.middle_name, pr.suffix, f.user_id;\n", nativeQuery = true)
//    List<MutualFollowersDTO> findMutualFollowersByUserIdAndFollowerId(@Param("userId") Long userId, @Param("followerId") Long followerId);

}
