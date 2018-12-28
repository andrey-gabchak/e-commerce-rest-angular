package com.gabchak.service;

import com.gabchak.model.User;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    Optional<User> getUserByEmail(String email);

    Optional<User> verifyPassword(User userByEmail, User user);

    List<User> getAll();

    User update(User user);

    User findById(Long id);

    void deleteByEmail(String email);

    void logout();

    User findByToken(String token);

    User findUserByCookies(Cookie[] cookies);

    User create(User user);

    Optional<User> findByEmail(String email);
}
