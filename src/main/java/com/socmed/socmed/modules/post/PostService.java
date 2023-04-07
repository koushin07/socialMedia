package com.socmed.socmed.modules.post;

import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.googleCloud.GoogleCloudStorageService;
import com.socmed.socmed.modules.profile.ProfileMapper;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserMapper;
import com.socmed.socmed.modules.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class PostService{

    @Autowired
    private  PostRepository postRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private GoogleCloudStorageService googleCloudStorageService;

    public List<Post> posts(){
        return postRepository.findAll();
    }

    public List<PostFeedsResponse> postOfUser(Long userId) {
        List<Post> allByUserId = postRepository.findAllByUserId(userId);
        System.out.println(allByUserId.get(0).getPostFile());
        List<PostFeedsResponse>  response= PostMapper.INSTANCE.feeds(allByUserId);
        for (PostFeedsResponse feed : response){
            for (Post post : allByUserId){
                feed.setPostFileName(post.getPostFile().getPostFileName());
                feed.setPostFileType(post.getPostFile().getPostFileType());
                feed.setPostFileURL(post.getPostFile().getPostFileURL());
            }
        }
        return response;
    }

    @Transactional
    public PostResponse createPost(PostCreationRequest request) throws ResourceNotFoundException {

        byte[] fileContent = Base64.getDecoder().decode(request.getPostFileContent());
        /**
         * checcking user
         */
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("no user found"));

        googleCloudStorageService.uploadFile(user.getUsername(),fileContent); // uploading file to google cloud store

        PostFile file = PostFile.builder()
                .postFileName(user.getUsername())
                .postFileType(request.getPostFileType())
                .postFileURL(googleCloudStorageService.getFileUrl(user.getUsername()))
                .build();

       Post post =   postRepository.save(Post.builder()
               .user(user)
               .postContent(request.getPostContent())
               .postFile(file)
               .build());
        PostResponse response = PostMapper.INSTANCE.createResponse(post);
        response.setUserDTO(
                UserMapper.INSTANCE.authenticateMapper(user)
        );

        return response;
    }

    public Post updatePost(Long id, PostCreationRequest request) throws ResourceNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post is not found"));

        post.setPostContent(request.getPostContent());
        return postRepository.save(post);
    }


    public List<PostFeedsResponse> getFeeds() {

List<Post> posts = postRepository.findAll();
        System.out.println(posts);
        return PostMapper.INSTANCE.feeds(posts);
    }
}
