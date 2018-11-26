package com.gabchak.controller.rest;

import com.gabchak.controller.external.model.UserDto;
import com.gabchak.model.User;
import com.gabchak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {

    private UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user")
    public ResponseEntity<UserDto> getByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(UserDto::of)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
