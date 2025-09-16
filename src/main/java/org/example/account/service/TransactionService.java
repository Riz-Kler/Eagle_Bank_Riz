package org.example.account.service;

import org.example.account.dto.BankAccountResponse;
import org.example.account.dto.TransactionDto;
import java.math.BigDecimal;

import java.util.List;

// minimal contract
public interface TransactionService {
    List<TransactionDto> listForAccount(String accountNumber);
    TransactionDto deposit(String accountNumber, BigDecimal amount, String description);
    TransactionDto withdraw(String accountNumber, BigDecimal amount, String description);
}