package org.example.account.service;

import org.example.account.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public List<TransactionDto> listForAccount(String accountNumber) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public TransactionDto deposit(String accountNumber, BigDecimal amount, String description) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public TransactionDto withdraw(String accountNumber, BigDecimal amount, String description) {
        throw new UnsupportedOperationException("TODO");
    }
}
