package org.example.user.service;

import org.example.user.dto.UserDtos.CreateUserRequest;
import org.example.user.dto.UserDtos.UpdateUserRequest;
import org.example.user.dto.UserDtos.UserResponse;
import org.example.user.model.User;
import org.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    private static UserResponse toDto(User u) {
        return new UserResponse(u.getId(), u.getFullName(), u.getEmail());
    }

    @Override
    public UserResponse create(CreateUserRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already in use");
        }
        User u = new User();
        u.setFullName(req.fullName());
        u.setEmail(req.email());
        // TODO: hash with BCrypt later
        u.setPasswordHash("{noop}" + req.password()); // demo only
        repo.save(u);
        return toDto(u);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> list() {
        return repo.findAll().stream().map(UserServiceImpl::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse get(Long id) {
        User u = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return toDto(u);
    }

    @Override
    public UserResponse update(Long id, UpdateUserRequest req) {
        User u = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        u.setFullName(req.fullName());
        return toDto(u);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("User not found");
        repo.deleteById(id);
    }
}
