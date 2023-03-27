package com.socmed.socmed.modules.reaction;

import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.modules.post.Post;
import com.socmed.socmed.modules.post.PostRepository;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record ReactionService(
        ReactionRepository reactionRepository,
        UserRepository userRepository,
        PostRepository postRepository
) {


    public void createReactionPost(ReactCreationRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new ResourceNotFoundException("post not found"));

        reactionRepository.save(Reaction.builder()
                .reactionType(request.getReactionType())
                .user(user)
                .post(post)
                .build());

    }

    public void updateReactionPost(Reaction request) {
        /*   validate if this reaction does exist   */
        Reaction reaction = reactionRepository.findById(request.getId())
                .orElseThrow(()-> new ResourceNotFoundException("reaction not found"));
        reaction.setReactionType(request.getReactionType());
        reactionRepository.save(reaction);
    }

    public void deleteReactionPost(Long id){
        Reaction reaction = reactionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("reaction not found cant delete"));
        reactionRepository.delete(reaction);
    }
}
