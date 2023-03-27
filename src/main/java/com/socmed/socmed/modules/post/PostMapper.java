package com.socmed.socmed.modules.post;

import com.socmed.socmed.modules.profile.ProfileMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

 PostMapper   INSTANCE = Mappers.getMapper(PostMapper .class);


 @Mapping(target = "userDTO", source = "post.user", ignore = true)
    PostResponse createResponse(Post post);


 @Mapping(target = "postContent", source = "post.postContent")
 List<PostFeedsResponse> createFeeds(List<Post> posts);


}
