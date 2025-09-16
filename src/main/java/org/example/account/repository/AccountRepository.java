package org.example.account.repository;

import org.example.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByIdAndUserId(String id, String userId);

    Optional<Account> findByAccountNumberAndUserId(String accountNumber, String userId);

    List<Account> findAllByUserId(String userId);

    void deleteByAccountNumber(String accountNumber);
}
