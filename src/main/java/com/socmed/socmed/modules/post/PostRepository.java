package com.socmed.socmed.modules.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(value = "SELECT p.*  FROM posts p\n " +
            "JOIN  users u ON u.id = p.user_id\n " +
            "JOIN follows f ON f.follower_id = u.id\n " +
            "WHERE f.user_id = :userId" , nativeQuery = true)
    List<Post> findAllByUserId(@Param("userId") Long userId);


    @Query("SELECT p FROM Post p INNER JOIN p.comments c")
    List<Post> findPostWithComments();

}
