package com.mar.service;

import com.mar.api.dto.UsersDto;
import com.mar.db.entity.Users;
import com.mar.db.mapper.UsersMapper;
import com.mar.db.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository repository;
    private final UsersMapper mapper;

    @Override
    public UsersDto getUser(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public List<UsersDto> getAllUsers() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public UsersDto create(UsersDto dto) {
        return mapper.toDto(
                repository.save(mapper.toEntity(dto))
        );
    }

    @Override
    public UsersDto update(UsersDto dto) {
        if (dto == null) {
            throw new RuntimeException("Cannot update user: DTO is null.");
        }
        if (dto.getId() == null) {
            throw new RuntimeException("Cannot update user: ID is null.");
        }

        Users user = repository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Not find user by id=" + dto.getId()));
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        return mapper.toDto(repository.save(user));
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
