package com.mar.api;

import com.mar.api.dto.SubscriptionsDto;
import com.mar.api.dto.UsersDto;
import com.mar.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users/{id}/subscriptions")
public class SubscriptionApi {

    private final UserService userService;

    @PostMapping
    public SubscriptionsDto addSubscription(@PathVariable("id") Long id,@RequestBody SubscriptionsDto dto) {
        log.debug("Add subscription {} to user with id = {}", dto, id);
        return new SubscriptionsDto();
    }

    @GetMapping
    public List<SubscriptionsDto> getSubscription(@PathVariable("id") Long id) {
        log.debug("Get user's subscriptions with user id = {}", id);
        return userService.getUser(id).getSubscriptions();
    }

    @DeleteMapping("/{sub_id}")
    public List<SubscriptionsDto> getSubscription(@PathVariable("id") Long id, @PathVariable("sub_id") Long subId) {
        log.debug("Delete subscriptions with id {} from user with id = {}", subId, id);
        return userService.getUser(id).getSubscriptions();
    }

}
