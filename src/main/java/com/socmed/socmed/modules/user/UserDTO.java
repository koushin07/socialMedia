package com.socmed.socmed.modules.user;

import com.socmed.socmed.modules.profile.Profile;
import com.socmed.socmed.modules.role.Role;
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



    private Role role;


}
