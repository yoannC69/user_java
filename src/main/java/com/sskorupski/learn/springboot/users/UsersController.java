package com.sskorupski.learn.springboot.users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user != null) return ResponseEntity.ok(user);
        else return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody User user) {
        if (userService.update(user)) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        if (userService.delete(user)) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

}