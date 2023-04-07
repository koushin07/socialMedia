package com.socmed.socmed.modules.profile;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Embeddable
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProfilePicture {
    private String ProfilePictureURL;
    private String ProfilePictureName;
    private String ProfilePictureType;

}
