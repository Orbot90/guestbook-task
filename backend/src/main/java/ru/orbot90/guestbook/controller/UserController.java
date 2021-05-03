package ru.orbot90.guestbook.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.orbot90.guestbook.model.SignUpRequest;
import ru.orbot90.guestbook.model.User;
import ru.orbot90.guestbook.services.TokenService;
import ru.orbot90.guestbook.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @ResponseBody
    public User registerUser(@RequestBody @Valid SignUpRequest request) {
        // TODO: consider returning JWT token
        return userService.createUser(request);
    }

    @GetMapping
    @ResponseBody
    public User currentUser(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        User user = new User();

        user.setName(authentication.getName());
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        user.setRoles(roles);
        user.setToken(this.tokenService.getToken(user));

        return user;
    }

}
