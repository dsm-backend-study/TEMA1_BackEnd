package com.example.todo.domain.user.controller;

import com.example.todo.domain.user.controller.dto.request.*;
import com.example.todo.domain.user.service.UserService;
import com.example.todo.security.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public void signup(
            @RequestBody SignUpRequest request
    ) {
        userService.signup(request);
    }

    @PostMapping("/login")
    public TokenResponse login(
            @RequestBody @Validated LoginRequest request
    ) {
        return userService.login(request);
    }

    @DeleteMapping("/remove/{userId}")
    public void removeUser(
            @PathVariable Long userId
    ) {
        userService.removeUser(userId);
    }

    @PutMapping("/modify/{userId}")
    public void userModify(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request
    ) {
        userService.modifyUser(userId, request);
    }
}
