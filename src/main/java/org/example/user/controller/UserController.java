package org.example.user.controller;

import org.example.user.dto.CreateUserRequest;
import org.example.user.dto.UserResponse;
import org.example.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users/") // note trailing slash to match test/spec
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest request) {
        var response = userService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)   // <-- was OK(200); must be 201
                .body(response);
    }
}
