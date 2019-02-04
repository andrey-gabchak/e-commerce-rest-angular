package com.gabchak.services.impl;

import com.gabchak.models.User;
import com.gabchak.repositories.UserRepository;
import com.gabchak.services.UserService;
import com.gabchak.services.dto.UserDto;
import com.gabchak.services.dto.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


//    TODO: use spring security instead manual methods

    @Override
    public UserDto register(UserDto userDto) {
        String hashedPassword = hashPassword(userDto.getPassword());

        User user = userMapper.map(userDto, User.class);

        user.setToken(getToken());
        user.setPassword(hashedPassword);

        return userMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public void logout() {
        //TODO: write logic
    }

    @Override
    public Optional<UserDto> findByToken(String token) {
        return userRepository.findByToken(token)
                .map(user -> userMapper.map(user, UserDto.class));
    }

    @Override
    public Optional<UserDto> verifyPassword(UserDto userByEmail, UserDto user) {
        String hashedPassword = hashPassword(user.getPassword());

        return hashedPassword.equals(userByEmail.getPassword())
                ? Optional.of(userByEmail) : Optional.empty();
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.map(userDto, User.class);
        return userMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public Optional<UserDto> findById(Integer id) {
        return userRepository.findById(id)
                .map(user -> userMapper.map(user, UserDto.class));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.mapAsList(userRepository.findAll(), UserDto.class);

    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> userMapper.map(user, UserDto.class));
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    private String hashPassword(String password) {
        MessageDigest digest;
        byte[] encodedHash = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytesToHex(encodedHash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
