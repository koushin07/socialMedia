package com.socmed.socmed.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", ignore = true)
    void updateCommentFromDto(CommentDTO dto, @MappingTarget Comment model);

    Comment dtoToModel(CommentDTO dto);
    CommentDTO modelToDto(Comment model);

}
