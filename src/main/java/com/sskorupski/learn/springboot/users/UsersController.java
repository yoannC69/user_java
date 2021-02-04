package com.sskorupski.learn.springboot.users;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// L’annotation déclare que la classe contient des endpoint
@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping(value = "/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/adduser")
    public List<User> addUser() {
        return userService.getAll();
    }

    @GetMapping(value = "/user/id")
    public List<User> getUserById() {
        return userService.getAll();
    }

    @GetMapping(value = "/update/id")
    public List<User> update() {
        return userService.getAll();
    }

    @GetMapping(value = "/delete/id")
    public List<User> deleteUser() {
        return userService.getAll();
    }

}
