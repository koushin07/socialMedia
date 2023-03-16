package com.socmed.socmed.modules.post;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreationRequest {

    @NotEmpty(message = "please provide user id")
    private Long userId;
    @NotEmpty(message = "no content?")
    private String postContent;
}
