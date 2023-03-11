package com.socmed.socmed.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCreationRequest {


    private Long userId;
    private String firstName;
    private String lastName;
    private String suffix;
    private String middleName;

    private String profilePicURL;
    private String bio;
}
