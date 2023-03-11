package com.socmed.socmed.comment;

import com.socmed.socmed.exception.ResourceNotFoundException;

import java.util.List;

public record CommentService(
        CommentRepository commentRepository
) {


    public List<Comment> comments(){
        return commentRepository.findAll();
    }

    public Comment createComment(CommentDTO commentDTO){
        Comment comment = CommentMapper.INSTANCE.dtoToModel(commentDTO);
        return commentRepository.save(comment);
    }
    public Comment updateComment(Long id, CommentDTO commentDTO) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found to update"));
        CommentMapper.INSTANCE.updateCommentFromDto(commentDTO, comment);

        return commentRepository.save(comment);
    }
}
