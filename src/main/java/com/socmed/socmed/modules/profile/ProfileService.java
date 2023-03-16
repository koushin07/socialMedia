package com.socmed.socmed.modules.profile;


import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.exception.UniqueException;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record ProfileService(
        ProfileRepository profileRepository,
        UserRepository userRepository
) {
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

    public boolean deleteProfile(Long id) {
        if (profileRepository.existsById(id)) {
            profileRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }



}
