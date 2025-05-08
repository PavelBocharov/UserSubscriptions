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
@RequestMapping(value = "/users")
public class UsersApi {

    private final UserService userService;

    @GetMapping("/{id}")
    public UsersDto getUser(@PathVariable("id") Long id) {
        log.debug("Get user by id = {}", id);
        return userService.getUser(id);
    }

    @GetMapping
    public List<UsersDto> getAll() {
        log.debug("Get all users");
        return userService.getAllUsers();
    }

    @PostMapping
    public UsersDto create(@RequestBody UsersDto dto) {
        log.debug("Create user: {}", dto);
        return userService.create(dto);
    }

    @PutMapping
    public UsersDto update(@RequestBody UsersDto dto) {
        log.debug("Update user: {}", dto);
        return userService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        log.debug("Remove user by id: {}", id);
        userService.remove(id);
    }

    @PostMapping("/{id}/subscription")
    public SubscriptionsDto addSubscription(@PathVariable("id") Long id,@RequestBody SubscriptionsDto dto) {
        log.debug("Add subscription {} to user with id = {}", dto, id);
        return new SubscriptionsDto();
    }

    @GetMapping("/{id}/subscription")
    public List<SubscriptionsDto> getSubscription(@PathVariable("id") Long id) {
        log.debug("Get user's subscriptions with user id = {}", id);
        return userService.getUser(id).getSubscriptions();
    }



}
