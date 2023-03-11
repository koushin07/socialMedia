package com.socmed.socmed.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private String comment;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
