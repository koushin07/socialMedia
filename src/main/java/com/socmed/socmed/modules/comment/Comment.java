package com.socmed.socmed.modules.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socmed.socmed.modules.post.Post;
import com.socmed.socmed.modules.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "comments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE comments SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Comment {
    @Id
    @Column(name = "comment_id")
    @SequenceGenerator(sequenceName = "comment_sequence", name = "comment_sequence")
    @GeneratedValue(strategy = SEQUENCE, generator = "comment_sequence")
    private Long id;
    @NotEmpty
    private String CommentContent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;
}
