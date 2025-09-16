package org.example.account.controller;

import org.example.account.dto.TransactionDto;
import org.example.account.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/accounts/{accountNumber}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionDto> list(@PathVariable String accountNumber) {
        return transactionService.listForAccount(accountNumber);
    }

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto deposit(@PathVariable String accountNumber,
                                  @RequestParam BigDecimal amount,
                                  @RequestParam(required = false) String description) {
        return transactionService.deposit(accountNumber, amount, description);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto withdraw(@PathVariable String accountNumber,
                                   @RequestParam BigDecimal amount,
                                   @RequestParam(required = false) String description) {
        return transactionService.withdraw(accountNumber, amount, description);
    }
}
