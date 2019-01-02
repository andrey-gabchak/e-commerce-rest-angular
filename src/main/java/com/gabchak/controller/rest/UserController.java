package com.gabchak.controller.rest;


import com.gabchak.model.User;
import com.gabchak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users/")
class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return Optional.of(userService.getAll())
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return Optional.of(userService.create(user))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Optional<User>> getByEmail(@PathVariable String email) {

        return Optional.ofNullable(userService.findByEmail(email))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);

    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.findByEmail(user.getEmail())
                .map(u -> { user.setId(u.getId()); return u; })
                .map(u -> { user.setToken(u.getToken()); return u; })
                .map(u -> { user.setPassword(u.getPassword()); return user; })
                .map(userService::update)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound():: build);
    }

    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteByEmail(email);
    }

}