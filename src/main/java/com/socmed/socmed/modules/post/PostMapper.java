package com.socmed.socmed.modules.post;

import com.socmed.socmed.modules.profile.ProfileMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

 PostMapper   INSTANCE = Mappers.getMapper(PostMapper.class);


 @Mapping(target = "userDTO", source = "post.user", ignore = true)
    PostResponse createResponse(Post post);


// @Mapping(target = "file.postContent", source = "post.postContent")
// @Mapping(target = "file.postFileType", source = "post.postFile.postFileType")
// @Mapping(target = "file.postFileURL", source = "post.postFile.postFileURL")
// List<PostFeedsResponse> createFeeds(List<Post> posts);
//

// @Mapping(target = "postContent", source = "posts.postContent")
// @Mapping(target = "file.postFileType", source = "postFileType")
// @Mapping(target = "file.postFileURL", source = "postFileURL")
 List<PostFeedsResponse> feeds(List<Post> posts);

// @AfterMapping
//    default void feeds(List<Post> posts, @MappingTarget PostFeedsResponse response){
//
// }

}
