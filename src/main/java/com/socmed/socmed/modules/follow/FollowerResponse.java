package com.socmed.socmed.modules.follow;

import com.socmed.socmed.modules.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowerResponse {

    private UserDTO user;

    private Long mutualFriends;
}
