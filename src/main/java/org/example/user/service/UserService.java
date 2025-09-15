package org.example.user.service;

import org.example.user.dto.CreateUserRequest;
import org.example.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(CreateUserRequest req);
    UserResponse getById(String userId);
    List<UserResponse> list();
}
