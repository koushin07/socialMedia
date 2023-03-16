package com.socmed.socmed.modules.profile;


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

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "profiles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE profiles SET deleted_at = now() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Profile {


    @Id
    @SequenceGenerator(sequenceName = "profile_sequence", name = "profile_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "profile_sequence")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
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
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;

}
