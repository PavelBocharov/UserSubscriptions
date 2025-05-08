package com.mar.service;

import com.mar.api.dto.SubscriptionsDto;
import com.mar.db.entity.Subscriptions;
import com.mar.db.entity.Users;
import com.mar.db.mapper.SubscriptionsMapper;
import com.mar.db.repository.SubscriptionRepository;
import com.mar.db.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UsersRepository usersRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionsMapper subscriptionsMapper;

    @Override
    public SubscriptionsDto create(SubscriptionsDto dto) {
        Subscriptions subscriptions = subscriptionRepository.findByTitle(dto.getTitle());
        if (subscriptions == null) {
            subscriptions = subscriptionRepository.save(subscriptionsMapper.toEntity(dto));
        }
        return subscriptionsMapper.toDto(subscriptions);
    }

    @Override
    @Transactional
    public void link(Long userId, Long subId) {
        Subscriptions subscriptions = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new RuntimeException("Not find subscriptions by id = " + subId));
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not find user by id = " + userId));

        if (user.getSubscriptions() == null) {
            List<Subscriptions> list = new ArrayList<>();
            list.add(subscriptions);
            user.setSubscriptions(list);
            usersRepository.save(user);
        } else if (user.getSubscriptions().stream().noneMatch(sub -> sub.getId().equals(subId))) {
            List<Subscriptions> list = user.getSubscriptions();
            list.add(subscriptions);
            user.setSubscriptions(list);
            usersRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void removeLink(Long userId, Long subId) {
        Subscriptions subscriptions = subscriptionRepository.findById(subId)
                .orElseThrow(() -> new RuntimeException("Not find subscriptions by id = " + subId));
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not find user by id = " + userId));

        if (user.getSubscriptions() == null || user.getSubscriptions().isEmpty()) {
            return;
        }

        if (user.getSubscriptions().remove(subscriptions)) {
            usersRepository.save(user);
        }
    }

    @Override
    public void remove(Long id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public List<SubscriptionsDto> getByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId).stream().map(subscriptionsMapper::toDto).toList();
    }

    @Override
    public List<SubscriptionsDto> getTop3Subscription() {
        return subscriptionRepository.getTop3Subscription()
                .stream().map(subscriptionsMapper::toDto)
                .toList();
    }
}
