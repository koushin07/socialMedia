package com.socmed.socmed.modules.post;

import com.socmed.socmed.modules.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {


    private Long id;
    private String postContent;
    private UserDTO userDTO;

}
