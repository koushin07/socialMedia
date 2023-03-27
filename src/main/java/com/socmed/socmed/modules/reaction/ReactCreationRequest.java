package com.socmed.socmed.modules.reaction;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReactCreationRequest {


    private Long PostId;
    private Long userId;
    private ReactionType reactionType;

}
