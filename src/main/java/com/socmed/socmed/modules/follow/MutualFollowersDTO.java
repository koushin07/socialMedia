package com.socmed.socmed.modules.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MutualFollowersDTO {

    private String first_name;

    private String last_name;
    private String middle_name;
    private String suffix;
    private Long mutual;


}
