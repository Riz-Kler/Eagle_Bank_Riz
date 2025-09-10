package org.example.user.service;

import org.example.user.dto.UserDtos.CreateUserRequest;
import org.example.user.dto.UserDtos.UpdateUserRequest;
import org.example.user.dto.UserDtos.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(CreateUserRequest req);
    List<UserResponse> list();
    UserResponse get(Long id);
    UserResponse update(Long id, UpdateUserRequest req);
    void delete(Long id);
}
