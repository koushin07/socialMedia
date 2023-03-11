package com.socmed.socmed.profile;

import com.socmed.socmed.user.User;
import jakarta.persistence.Embeddable;
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
