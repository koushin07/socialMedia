package com.socmed.socmed.user;

import com.socmed.socmed.profile.Profile;
import com.socmed.socmed.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;

    private Profile profiles;

    private Role role;


}
