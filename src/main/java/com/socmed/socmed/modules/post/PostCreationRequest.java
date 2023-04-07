package com.socmed.socmed.modules.post;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreationRequest {


    private Long userId;
    @NotEmpty(message = "no content?")
    private String postContent;

    private String postFileContent;
    private String postFileType;
    private String postFileURL;
}
