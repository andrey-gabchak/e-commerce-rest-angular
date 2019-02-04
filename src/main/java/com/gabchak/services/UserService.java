package com.gabchak.services;

import com.gabchak.services.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto register(UserDto userDto);

    UserDto save(UserDto userDto);

    Optional<UserDto> verifyPassword(UserDto userByEmail, UserDto user);

    List<UserDto> findAll();

    Optional<UserDto> findById(Integer id);

    void deleteByEmail(String email);

    void logout();

    Optional<UserDto> findByToken(String token);

    Optional<UserDto> findByEmail(String email);
}
