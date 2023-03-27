package com.socmed.socmed.modules.post;


import com.fasterxml.jackson.annotation.JsonIgnore;

import com.socmed.socmed.modules.comment.Comment;
import com.socmed.socmed.modules.reaction.Reaction;
import com.socmed.socmed.modules.user.User;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "posts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE posts SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Post {

    @Id
    @SequenceGenerator(sequenceName = "post_sequence", name = "post_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    private Long id;
    private String postContent;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", fetch = EAGER)
    private Collection<Reaction> reactions;

    @OneToMany(mappedBy = "post", fetch = EAGER)
    private Collection<Comment> comments;
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private Timestamp deletedAt;


}
