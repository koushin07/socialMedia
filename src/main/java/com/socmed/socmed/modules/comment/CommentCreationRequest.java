package com.socmed.socmed.modules.comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreationRequest {

    private Long postId;
    private Long userId;

    @NotEmpty(message = "your comment an empty content")
    private String content;

}
