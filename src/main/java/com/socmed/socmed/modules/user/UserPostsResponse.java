package com.socmed.socmed.modules.user;

import com.socmed.socmed.modules.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostsResponse {

    private Long userid;
    private Collection<Post> posts;
}
