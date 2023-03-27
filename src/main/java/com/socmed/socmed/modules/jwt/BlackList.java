package com.socmed.socmed.modules.jwt;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "black_lists")
public class BlackList {

    @Column(unique = true, nullable = false)
    @Id
    private String accessToken;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

}
