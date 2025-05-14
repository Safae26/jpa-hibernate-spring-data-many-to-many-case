package net.safae.jpa_many_to_many_case.service;

import net.safae.jpa_many_to_many_case.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users/{username}")
    public User user(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        return user;
    }
}
