package org.example.account.service;

import org.example.account.dto.CreateBankAccountRequest;
import org.example.account.dto.BankAccountResponse;

public interface AccountService {
    BankAccountResponse create(CreateBankAccountRequest req);


}
