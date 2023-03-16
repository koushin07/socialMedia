package com.socmed.socmed.modules.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

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
