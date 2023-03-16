package com.socmed.socmed.modules.profile;

import com.socmed.socmed.modules.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {


    private Long id;
    private User user;
    private String firstName;
    private String lastName;
    private String suffix;
    private String middleName;

    private String profilePicURL;
    private String bio;


}
