package com.gabchak.controllers.external.model;

import com.gabchak.validators.annotations.FieldMatch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch.Field({
        @FieldMatch(
                message = "Password doesn't match",
                field = "password",
                fieldMatch = "verifiedPassword")
})
public class RegisterUserDto {

    @NotNull
    private String email;
    @NotNull
    private String password;
    private String verifiedPassword;
    private String firstName;
    private String lastName;
    private String token;

    public static RegisterUserDto empty() {
        return new RegisterUserDto();
    }
}
