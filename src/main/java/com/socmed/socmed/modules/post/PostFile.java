package com.socmed.socmed.modules.post;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostFile {


    private String postFileName;
    private String postFileType;
    private String postFileURL;
}
