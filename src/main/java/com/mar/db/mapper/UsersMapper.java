package com.mar.db.mapper;

import com.mar.api.dto.UsersDto;
import com.mar.db.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        uses = {SubscriptionsMapper.class},
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface UsersMapper {

    UsersDto toDto(Users users);

    Users toEntity(UsersDto dto);

}
