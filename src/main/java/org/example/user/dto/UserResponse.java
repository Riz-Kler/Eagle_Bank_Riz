package org.example.user.dto;

import java.time.OffsetDateTime;

public class UserResponse {
    private String id; // String to match DB and spec
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private OffsetDateTime createdTimestamp;
    private OffsetDateTime updatedTimestamp;

    // getters/setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public OffsetDateTime getCreatedTimestamp() { return createdTimestamp; }
    public void setCreatedTimestamp(OffsetDateTime createdTimestamp) { this.createdTimestamp = createdTimestamp; }
    public OffsetDateTime getUpdatedTimestamp() { return updatedTimestamp; }
    public void setUpdatedTimestamp(OffsetDateTime updatedTimestamp) { this.updatedTimestamp = updatedTimestamp; }
}
