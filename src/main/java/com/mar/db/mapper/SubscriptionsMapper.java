package com.mar.db.mapper;

import com.mar.api.dto.SubscriptionsDto;
import com.mar.db.entity.Subscriptions;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionsMapper {

    SubscriptionsDto toDto(Subscriptions subscriptions);

    Subscriptions toEntity(SubscriptionsDto dto);

}
