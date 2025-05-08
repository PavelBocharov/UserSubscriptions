package com.mar.service;

import com.mar.api.dto.UsersDto;

import java.util.List;

public interface UserService {

    UsersDto getUser(Long id);

    List<UsersDto> getAllUsers();

    UsersDto create(UsersDto dto);

    UsersDto update(UsersDto dto);

    void remove(Long id);

}
