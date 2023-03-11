package com.socmed.socmed.comment;

import com.socmed.socmed.post.Post;
import com.socmed.socmed.profile.Profile;
import com.socmed.socmed.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "comments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @SequenceGenerator(sequenceName = "comment_sequence", name = "comment_sequence")
    @GeneratedValue(strategy = SEQUENCE, generator = "comment_sequence")
    private Long id;
    @NotEmpty
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column
    @CreationTimestamp
    private Timestamp createdAt;

    @Column
    @UpdateTimestamp
    private Timestamp updatedAt;
}
