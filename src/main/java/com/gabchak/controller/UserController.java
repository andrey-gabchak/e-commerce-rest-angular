package com.gabchak.controller;

import com.gabchak.controller.external.model.RegisterUserDto;
import com.gabchak.model.User;
import com.gabchak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {


    private  UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView vm = new ModelAndView();
        vm.setViewName("login");
        vm.addObject("user", new User());
        return vm;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView vm) {

        vm.setViewName("register");
        vm.addObject("userDto", RegisterUserDto.empty());
        return vm;
    }

    @PostMapping("/register")
    public String register(@Valid RegisterUserDto userDto, BindingResult bindingResult, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = User.of(userDto);
        userService.addUser(user);
        User userFromDB = userService.getUserByEmail(user.getEmail()).get();
        response.addCookie(new Cookie("MATE", userFromDB.getToken()));

        return "home";
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute(value = "user") User user, ModelAndView vm, HttpServletResponse response) {
        return userService.getUserByEmail(user.getEmail())
                .map(r -> userService.verifyPassword(r, user))
                .map(r -> {
                    String token = userService.getUserByEmail(user.getEmail()).get().getToken();
                    Cookie cookie = new Cookie("MATE", token);
                    response.addCookie(cookie);
                    vm.setViewName("home");
                    return vm;
                })
                .orElseGet(() -> {
                    vm.setViewName("login");
                    return vm;
                });
    }

    @GetMapping("/admin/users")
    public ModelAndView shotUsersAdminList(ModelAndView vm) {
        vm.setViewName("usersAdminList");
        vm.addObject("users", userService.findAll());
        return vm;
    }

    @GetMapping("/admin/users/edit_{id}")
    public ModelAndView showEditUserPage(@PathVariable Long id, ModelAndView vm) {
        vm.setViewName("userEdit");
        vm.addObject("user", userService.findById(id));
        return vm;
    }

    @PostMapping("/admin/users/save_{id}")
    public ModelAndView editUserPage(@PathVariable Long id, User user, ModelAndView vm) {
        user.setId(id);
        userService.update(user);
        vm.setViewName("usersAdminList");
        vm.addObject("users", userService.findAll());
        return vm;
    }

    @PostMapping("/admin/users/delete_{id}")
    public ModelAndView deleteUser(@PathVariable Long id, ModelAndView vm) {
        userService.delete(id);
        vm.setViewName("usersAdminList");
        vm.addObject("users", userService.findAll());
        return vm;
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView vm) {
        userService.logout();
        vm.setViewName("login");
        vm.addObject("user", new User());
        return vm;
    }
}
