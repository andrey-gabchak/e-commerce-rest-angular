package com.gabchak.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    @NotNull
    private String email;
    @NotNull
    @JsonIgnore
    private String password;
    private String verifiedPassword;
    private String firstName;
    private String lastName;
    private String token;
}
