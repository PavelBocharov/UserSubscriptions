package com.mar.service;

import com.mar.api.dto.SubscriptionsDto;

import java.util.List;

public interface SubscriptionService {

    SubscriptionsDto create(SubscriptionsDto dto);

    void link(Long userId, Long subId);

    void removeLink(Long userId, Long subId);

    void remove(Long id);

    List<SubscriptionsDto> getByUserId(Long userId);

    List<SubscriptionsDto> getTop3Subscription();

}
