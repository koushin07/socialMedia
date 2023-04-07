package com.socmed.socmed.modules.post;

import com.socmed.socmed.modules.comment.Comment;
import com.socmed.socmed.modules.comment.CommentDTO;
import com.socmed.socmed.modules.reaction.Reaction;
import com.socmed.socmed.modules.reaction.ReactionDTO;
import com.socmed.socmed.modules.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostFeedsResponse {

    private String postContent;

    private String postFileName;

    private String postFileType;

    private String postFileURL;

    private Collection<ReactionDTO> reactions;

   private Collection<CommentDTO> comments;

   private UserDTO user;

}



