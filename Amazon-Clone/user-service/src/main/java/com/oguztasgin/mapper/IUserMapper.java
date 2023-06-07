package com.oguztasgin.mapper;

import com.oguztasgin.dto.request.NewCreateUserRequestDto;
import com.oguztasgin.dto.response.UserFindAllResponseDto;
import com.oguztasgin.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);

    UserProfile toUserProfile(final NewCreateUserRequestDto dto);

    @Mapping(source = "id",target = "userId")
    UserFindAllResponseDto toUserFindAllResponseDto(UserProfile userProfile);
}
