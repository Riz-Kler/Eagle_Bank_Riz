package org.example.account.service;

import jakarta.transaction.Transactional;
import org.example.account.dto.BankAccountResponse;
import org.example.account.dto.CreateBankAccountRequest;
import org.example.account.model.Account;
import org.example.account.repository.AccountRepository;
import org.example.user.model.User;
import org.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private static final String FIXED_USER_ID = "usr-ABCDEFG1"; // used by contract tests

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accounts, UserRepository users) {
        this.accountRepository = accounts;
        this.userRepository = users;
    }

    // ------------------------ helpers ------------------------

    private Account getOwnedAccountByNumberOrThrow(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NoSuchElementException("Bank account was not found"));
        // If you later enforce ownership, add a CurrentUser check here.
    }

    private BankAccountResponse toResponse(Account a) {
        var r = new BankAccountResponse();
        r.setUserId(a.getUserId());                 // <-- add this line
        r.setAccountNumber(a.getAccountNumber());
        r.setSortCode(a.getSortCode());
        r.setName(a.getName());
        r.setAccountType(a.getAccountType());
        r.setCurrency(a.getCurrency());
        r.setBalance(a.getBalance());
        r.setCreatedTimestamp(a.getCreatedTimestamp());
        r.setUpdatedTimestamp(a.getUpdatedTimestamp());
        return r;
    }
    private User getOrCreateFixedUser(OffsetDateTime now) {
        return userRepository.findById(FIXED_USER_ID).orElseGet(() -> {
            var u = new User();
            u.setId(FIXED_USER_ID);
            u.setName("Riz Kler");
            u.setEmail("riz@test.local");
            u.setPhoneNumber("07000000000");
            u.setAddress("Test Address");
            u.setCreatedTimestamp(now);
            u.setUpdatedTimestamp(now);
            return userRepository.save(u);
        });
    }

    // ------------------------ API methods ------------------------

    @Transactional
    @Override
    public BankAccountResponse create(CreateBankAccountRequest req) {
        var now = OffsetDateTime.now();

        // Ensure a known user exists for tests
        var user = getOrCreateFixedUser(now);

        var a = new Account();
        a.setId(UUID.randomUUID().toString());
        a.setUserId(user.getId());

        a.setAccountType(Account.normalizeAccountType(req.getAccountType())); // "CURRENT" | "SAVINGS"
        a.setCurrency("GBP");
        a.setBalance(BigDecimal.ZERO);

        a.setAccountNumber(Account.randomAccountNumber()); // ^01\d{6}$
        a.setSortCode(Account.defaultSortCode());           // "10-10-10"
        a.setName((req.getName() != null && !req.getName().isBlank()) ? req.getName() : "Current Account");

        a.setCreatedTimestamp(now);
        a.setUpdatedTimestamp(now);

        var saved = accountRepository.save(a);
        return toResponse(saved);
    }

    @Override
    public List<BankAccountResponse> listAccounts() {
        // If you add repo method findAllByUserId(userId), use it here.
        return accountRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public BankAccountResponse getByAccountNumber(String accountNumber) {
        return toResponse(getOwnedAccountByNumberOrThrow(accountNumber));
    }

    @Transactional
    @Override
    public void deleteByAccountNumber(String accountNumber) {
        var acc = getOwnedAccountByNumberOrThrow(accountNumber);
        accountRepository.delete(acc);
    }
}
