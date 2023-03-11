package com.socmed.socmed.profile;


import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.user.User;
import com.socmed.socmed.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public record ProfileService(
        ProfileRepository profileRepository,
        UserRepository userRepository
) {
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    public Profile createProfile(ProfileCreationRequest request) throws ResourceNotFoundException {

        if (request == null){
            throw new NullPointerException("request is null");
        }
        Profile newProfile = Profile.builder()
                .profilePicURL(request.getProfilePicURL())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .suffix(request.getSuffix())
                .bio(request.getBio())
                .user(userRepository.findById(request.getUserId())
                        .orElseThrow(()-> new ResourceNotFoundException("No such user to Update")))
                .build();

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
