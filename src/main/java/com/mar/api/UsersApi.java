package com.mar.api;

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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UsersApi {

    private final UserService userService;

    /**
     * Получение данных пользователя по id.
     *
     * @param id пользователя.
     * @return данные пользователя.
     */
    @GetMapping("/{id}")
    public UsersDto getUser(@PathVariable("id") Long id) {
        log.debug("Get user by id = {}", id);
        return userService.getUser(id);
    }

    /**
     * Получение данных ВСЕХ пользователей.
     *
     * @return список данных.
     */
    @GetMapping
    public List<UsersDto> getAll() {
        log.debug("Get all users");
        return userService.getAllUsers();
    }

    /**
     * Создание пользователя.
     *
     * @param dto данные пользователя.
     * @return сохраненные данные.
     */
    @PostMapping
    public UsersDto create(@RequestBody UsersDto dto) {
        log.debug("Create user: {}", dto);
        return userService.create(dto);
    }

    /**
     * Обновление данных пользователя.
     *
     * @param dto новые данные пользователя с ID.
     * @return сохраненные данные.
     */
    @PutMapping
    public UsersDto update(@RequestBody UsersDto dto) {
        log.debug("Update user: {}", dto);
        return userService.update(dto);
    }

    /**
     * Удаление пользователя по ID.
     *
     * @param id пользователя.
     */
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        log.debug("Remove user by id: {}", id);
        userService.remove(id);
    }

}
