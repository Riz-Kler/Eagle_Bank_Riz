package org.example.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDtos {

    public record CreateUserRequest(
            @NotBlank @Size(max = 120) String fullName,
            @NotBlank @Email @Size(max = 160) String email,
            @NotBlank @Size(min = 6, max = 100) String password
    ) {}

    public record UpdateUserRequest(
            @NotBlank @Size(max = 120) String fullName
    ) {}

    public record UserResponse(
            Long id,
            String fullName,
            String email
    ) {}
}
