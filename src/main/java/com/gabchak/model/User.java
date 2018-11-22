package com.gabchak.model;

import com.gabchak.controller.external.model.RegisterUserDto;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

public class User {


    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String firstName;
    private String lastName;
    private String token;

    public User(Long id, String email, String password, String firstName, String lastName, String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
    }

    public static User of(RegisterUserDto userDto) {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setToken(userDto.getToken());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public User() {
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

}
