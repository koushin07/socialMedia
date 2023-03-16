package com.socmed.socmed.modules.profile;

import com.socmed.socmed.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT * FROM profiles p WHERE p.user_id = :id", nativeQuery = true)
    Optional<Profile> findByUserId(@Param("id") Long id);
}
