package org.example.account.service;

import org.example.account.dto.CreateBankAccountRequest;
import org.example.account.dto.BankAccountResponse;

import java.util.List;

public interface AccountService {
    BankAccountResponse create(CreateBankAccountRequest req);


    List<BankAccountResponse> listAccounts();
    BankAccountResponse getByAccountNumber(String accountNumber);
    void deleteByAccountNumber(String accountNumber);
}
