package ru.orbot90.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.orbot90.guestbook.model.SignUpRequest;
import ru.orbot90.guestbook.model.User;
import ru.orbot90.guestbook.services.UserService;

import javax.validation.Valid;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public User registerUser(@RequestBody @Valid SignUpRequest request) {
        // TODO: consider returning JWT token
        return userService.createUser(request);
    }

}
