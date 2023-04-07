package com.socmed.socmed.modules.profile;


import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.exception.UniqueException;
import com.socmed.socmed.googleCloud.GoogleCloudStorageService;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class ProfileService {

    @Autowired
    private  ProfileRepository profileRepository;
    @Autowired
    private   UserRepository userRepository;
    @Autowired
    private GoogleCloudStorageService googleCloudStorageService;


    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    public Profile createProfile(ProfileCreationRequest request) throws ResourceNotFoundException {

        /*   check if this null   */
        if (request == null){
            throw new NullPointerException("request is null");
        }
        /*   check if user_id already exist in the profiles table   */
        Optional<Profile> oldProfile = profileRepository.findByUserId(request.getUserId());
        if (oldProfile.isPresent()){
            throw new UniqueException("This user has already profile");
        }
        /*   check if user even exist in the users table   */
        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new ResourceNotFoundException("no user found"));
        Profile newProfile = ProfileMapper.INSTANCE.profileCreation(request);
        newProfile.setUser(user);



        return profileRepository.save(newProfile);
    }

    public Profile updateProfile(Long id, ProfileDTO profileDTO) throws ResourceNotFoundException {
        Profile profileToUpdate = profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no profile found"));
        ProfileMapper.INSTANCE.updateProfileFromDto(profileDTO, profileToUpdate);
        return profileRepository.save(profileToUpdate);

    }

    @Transactional
    public Profile uploadProfilePic(Long id, MultipartFile file) throws IOException {

            Profile profile = profileRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("no user with profile found"));

            byte[] fileContent = file.getBytes();

            googleCloudStorageService.uploadFile(file.getOriginalFilename(), fileContent);

            String url = googleCloudStorageService.getFileUrl( file.getOriginalFilename());
            ProfilePicture picture = ProfilePicture.builder()
                    .ProfilePictureName(file.getOriginalFilename())
                    .ProfilePictureType(file.getContentType())
                    .ProfilePictureURL(googleCloudStorageService.getFileUrl( file.getOriginalFilename()))
                    .build();

            profile.setProfilePicture(picture);
            profileRepository.save(profile);
            return profileRepository.save(profile);


    }

    public boolean deleteProfile(Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }



}
