package com.socmed.socmed.modules.profile;


import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.googleCloud.GoogleCloudStorageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Profile> findProfile(@PathVariable Long id) {
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


    @PostMapping("/upload/{id}")
    public ResponseEntity<Profile> uploadProfilePic(
            @PathVariable("id") Long id,
            @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);


        return ResponseEntity.ok( profileService.uploadProfilePic(id, file));
    }



    /**
     * @// TODO: 3/16/23 change 2nd @param profileDTO
     * @param id
     * @param profileDTO
     * @return
     * @throws ResourceNotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) throws ResourceNotFoundException {
        Profile updatedProfile = profileService.updateProfile(id, profileDTO);
        return ResponseEntity.ok(updatedProfile);
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
