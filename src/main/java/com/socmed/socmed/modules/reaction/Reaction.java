package com.socmed.socmed.modules.reaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socmed.socmed.modules.post.Post;
import com.socmed.socmed.modules.reaction.ReactionType;
import com.socmed.socmed.modules.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "reactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE reactions SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Reaction {

    @Column(name = "reaction_id")
    @Id
    @SequenceGenerator(sequenceName = "reaction_sequence", name = "reaction_sequence")
    @GeneratedValue(strategy = SEQUENCE, generator = "reaction_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private Timestamp deletedAt;



}
