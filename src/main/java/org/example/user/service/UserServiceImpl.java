package org.example.user.service;

import org.example.user.dto.CreateUserRequest;
import org.example.user.dto.UserResponse;
import org.example.user.model.User;
import org.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse create(CreateUserRequest req) {
        var now = OffsetDateTime.now();

        var u = new User();
        u.setId(UUID.randomUUID().toString());
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setAddress(req.getAddress());
        u.setPhoneNumber(req.getPhoneNumber());
        u.setCreatedTimestamp(now);
        u.setUpdatedTimestamp(now);
        // passwordHash intentionally not set for this test

        var saved = userRepository.save(u);
        return toResponse(saved);
    }

    @Override
    public UserResponse getById(String userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + userId));
        return toResponse(user);
    }

    @Override
    public List<UserResponse> list() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    private UserResponse toResponse(User u) {
        var r = new UserResponse();
        r.setId(u.getId()); // String -> String
        r.setName(u.getName());
        r.setEmail(u.getEmail());
        r.setAddress(u.getAddress());
        r.setPhoneNumber(u.getPhoneNumber());
        r.setCreatedTimestamp(u.getCreatedTimestamp());
        r.setUpdatedTimestamp(u.getUpdatedTimestamp());
        return r;
    }
}
