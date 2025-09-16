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

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountService service;
    public AccountController(AccountService accountService) { this.service = accountService; }

    @PostMapping
    public ResponseEntity<BankAccountResponse> create(@Valid @RequestBody CreateBankAccountRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @GetMapping("/v1/accounts")
    public List<BankAccountResponse> list() {
        return service.listAccounts();
    }

    @GetMapping("/v1/accounts/{accountNumber}")
    public BankAccountResponse get(@PathVariable String accountNumber) {
        return service.getByAccountNumber(accountNumber);
    }

    @DeleteMapping("/v1/accounts/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String accountNumber) {
        service.deleteByAccountNumber(accountNumber);
    }

}