package com.socmed.socmed.modules.comment;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/comments")
@CrossOrigin(origins = "*")
public record CommentController(
        CommentService commentService
) {

    /**
     *
     * @param postId
     * @return list of comments of a post
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<Comment>> getAllCommentsOfPost(@PathVariable("id") Long postId) {
        List<Comment> comments = commentService.getAllCommentsOfPost(postId);
        return ok(comments);
    }

    /**
     *
     * @param request postId, userId, Content
     * @return status 201 created new comment
     */
    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody @Valid CommentCreationRequest request){
    Comment comment =commentService.createComment(request);
        return created(URI.create("")).body(comment);
    }

    /**
     *
     * @param id of comment
     * @param content new content of a comment
     * @return update comment
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody String content){
        Comment comment = commentService.updateComment(id, content);
        return ok(comment);
    }


}
