package com.socmed.socmed.profile;


import com.socmed.socmed.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@AllArgsConstructor
public class ProfileController {

    @Autowired
    ProfileService profileService;




    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileCreationRequest profile) throws ResourceNotFoundException {
        Profile createdProfile = profileService.createProfile(profile);
        return ResponseEntity.status(CREATED).body(createdProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) throws ResourceNotFoundException {
        Profile updatedProfile = profileService.updateProfile(id, profileDTO);
        if (updatedProfile != null) {
            return ResponseEntity.ok(updatedProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        boolean deleted = profileService.deleteProfile(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
