package org.example.account.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateBankAccountRequest {
    @NotBlank
    private String name;          // "Personal Bank Account"
    @NotBlank
    private String accountType;   // "personal"

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
}
