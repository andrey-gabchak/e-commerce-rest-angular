package com.gabchak.service;

import com.gabchak.model.User;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    Optional<User> getUserByEmail(String email);

    Optional<User> verifyPassword(User userByEmail, User user);

    List<User> findAll();

    void update(User user);

    User findById(Long id);

    void delete(Long id);

    void logout();

    User findByToken(String token);

    User findUserByCookies(Cookie[] cookies);
}
