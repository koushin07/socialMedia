package com.socmed.socmed.modules.post;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(value = "SELECT * FROM posts p WHERE p.user_id = :userId", nativeQuery = true)
    List<Post> findAllByUserId(@Param("userId") Long userId);
}
