package com.socmed.socmed.modules.comment;

import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.modules.post.Post;
import com.socmed.socmed.modules.post.PostRepository;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CommentService(
        CommentRepository commentRepository,
        UserRepository userRepository,
        PostRepository postRepository
) {


    public List<Comment> comments() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllCommentsOfPost(Long postId) {
        return commentRepository.findALlByPostId(postId);
    }

    public Comment createComment(CommentCreationRequest request) {
//        Comment comment = CommentMapper.INSTANCE.dtoToModel(commentDTO);
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundException("No User Found"));
        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new ResourceNotFoundException("No Post found"));
        Comment comment = Comment.builder()
                .CommentContent(request.getContent())
                .user(user)
                .post(post)
                .build();
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, String content) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found to update"));
        comment.setCommentContent(content);
        return commentRepository.save(comment);
    }

}
