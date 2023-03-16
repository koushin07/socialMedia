package com.socmed.socmed.modules.profile;

import com.socmed.socmed.exception.ResourceNotFoundException;
import com.socmed.socmed.modules.user.User;
import com.socmed.socmed.modules.user.UserRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = UserRepository.class)
public interface ProfileMapper {


   ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);


    @Mapping(target = "id", ignore = true)
   void updateProfileFromDto(ProfileDTO profileDTO, @MappingTarget Profile profile);

    ProfileDTO modelToDto(Profile profile);

    public abstract Profile dtoToModel(ProfileDTO profileDTO);


//    @Mapping(target = "user", expression = "java(getUserById(request.getUserId()))")
    @Mapping(target = "user", source = "request.userId", ignore = true)
    public abstract Profile profileCreation(ProfileCreationRequest request ) throws ResourceNotFoundException;

//    default User getUserById(long userId, ) throws ResourceNotFoundException {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
//    }


}
