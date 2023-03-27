package com.socmed.socmed.modules.follow;


import com.socmed.socmed.modules.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "follows")
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE follows SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
@IdClass(FollowerId.class)
public class Follow {

    @Id
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private Timestamp deletedAt;
}
