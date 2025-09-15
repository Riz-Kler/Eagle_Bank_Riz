package org.example.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    @JsonProperty("createdTimestamp")
    private OffsetDateTime createdTimestamp;

    @JsonProperty("updatedTimestamp")
    private OffsetDateTime updatedTimestamp;
}
