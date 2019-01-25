package com.gabchak.services;

import com.gabchak.models.User;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> verifyPassword(User userByEmail, User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void deleteByEmail(String email);

    void logout();

    User findByToken(String token);

    User create(User user);

    Optional<User> findByEmail(String email);
}
