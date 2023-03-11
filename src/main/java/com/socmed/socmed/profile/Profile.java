package com.socmed.socmed.profile;


import com.socmed.socmed.comment.Comment;
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
import java.util.Collection;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "profiles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {


    @Id
    @SequenceGenerator(sequenceName = "profile_sequence", name = "profile_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "profile_sequence")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    private String suffix;
    private String middleName;

    private String profilePicURL;
    private String bio;

    @Column
    @CreationTimestamp
    private Timestamp createdAt;

    @Column
    @UpdateTimestamp
    private Timestamp updatedAt;

}
