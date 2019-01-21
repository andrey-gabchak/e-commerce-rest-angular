package com.gabchak.controllers.external.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabchak.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    private String email;
    @NotNull
    @JsonIgnore
    private String password;
    private String verifiedPassword;
    private String firstName;
    private String lastName;
    private String token;

    public static UserDto of(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setToken(user.getToken());
        return userDto;
    }
}
