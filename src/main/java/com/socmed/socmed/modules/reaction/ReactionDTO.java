package com.socmed.socmed.modules.reaction;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.socmed.socmed.modules.post.Post;
import com.socmed.socmed.modules.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReactionDTO {
    private Long id;


    private User user;


    private ReactionType reactionType;

}

