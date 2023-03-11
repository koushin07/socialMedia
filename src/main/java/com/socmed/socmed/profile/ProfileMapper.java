package com.socmed.socmed.profile;

import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.user.User;
import com.socmed.socmed.user.UserRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = UserRepository.class)
public abstract class ProfileMapper {

    @Autowired
    private  UserRepository userRepository;

    public static final ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);


    @Mapping(target = "id", ignore = true)
    public abstract void updateProfileFromDto(ProfileDTO profileDTO, @MappingTarget Profile profile);

    public abstract ProfileDTO modelToDto(Profile profile);

    public abstract Profile dtoToModel(ProfileDTO profileDTO);

    @Mapping( target = "user", expression = "java(getUserById(request.getUserId()))")

    public abstract void profileCreation(ProfileCreationRequest request, @MappingTarget Profile profile) throws ResourceNotFoundException;

    @AfterMapping
    public User getUserById(long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

    }

}
