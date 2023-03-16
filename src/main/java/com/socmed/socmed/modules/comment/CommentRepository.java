package com.socmed.socmed.modules.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comments c WHERE c.post_id = :postId", nativeQuery = true)
    List<Comment> findALlByPostId(@Param("postId") Long postId);
}