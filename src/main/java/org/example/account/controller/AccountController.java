// src/main/java/org/example/account/controller/AccountController.java
package org.example.account.controller;

import jakarta.validation.Valid;
import org.example.account.dto.BankAccountResponse;
import org.example.account.dto.CreateBankAccountRequest;
import org.example.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountService service;
    public AccountController(AccountService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<BankAccountResponse> create(@Valid @RequestBody CreateBankAccountRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }
}