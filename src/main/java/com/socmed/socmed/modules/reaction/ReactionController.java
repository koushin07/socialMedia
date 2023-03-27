package com.socmed.socmed.modules.reaction;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/reactions")
@CrossOrigin(origins = "*")
@Slf4j
public record ReactionController(
        ReactionService reactionService
) {

    @PostMapping("/create")
    public ResponseEntity<String> createReactionPost(@RequestBody @Valid ReactCreationRequest request){
        reactionService.createReactionPost(request);
        return created(URI.create("")).body("");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateReactionPost(@RequestBody @Valid Reaction request){
        reactionService.updateReactionPost(request);
        return ok("");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReactionPost(@PathVariable Long id){
        reactionService.deleteReactionPost(id);
        return ok("");
    }
}
