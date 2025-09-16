package org.example.account.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(
        name = "accounts",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_accounts_account_number", columnNames = "account_number")
        }
)
public class Account {

    @Id
    @Column(name = "id", nullable = false, length = 255)
    private String id;

    @Column(name = "user_id", nullable = false, length = 255)
    private String userId;

    @Column(name = "account_type", nullable = false, length = 255)
    private String accountType; // e.g. CURRENT, SAVINGS

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;    // e.g. GBP

    @Column(name = "balance", nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "created_at")
    private OffsetDateTime createdTimestamp;

    @Column(name = "updated_at")
    private OffsetDateTime updatedTimestamp;

    // New columns used by your service / responses
    @Column(name = "account_number", length = 8)
    private String accountNumber;   // must match ^01\\d{6}$

    @Column(name = "sort_code", length = 8)
    private String sortCode;        // e.g. "10-10-10"

    @Column(name = "name", length = 120)
    private String name;            // display name for the account

    // ----- Helpers ----------------------------------------------------------

    /** Generate an 8-char account number that matches ^01\\d{6}$ */
    public static String randomAccountNumber() {
        int six = (int) (Math.random() * 1_000_000); // 000000 - 999999
        return String.format("01%06d", six);
    }

    /** Default sort-code for tests/seed data. */
    public static String defaultSortCode() {
        return "10-10-10";
    }

    /** Normalize free-form input into the canonical account type. */
    public static String normalizeAccountType(String input) {
        if (input == null) return "CURRENT";
        String v = input.trim().toUpperCase(Locale.ROOT);
        return switch (v) {
            case "personal", "current" -> "current";
            case "savings", "saving"   -> "savings";
            default                    -> "current";
        };
    }

    // ----- Getters / Setters -----------------------------------------------

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public OffsetDateTime getCreatedTimestamp() { return createdTimestamp; }
    public void setCreatedTimestamp(OffsetDateTime createdTimestamp) { this.createdTimestamp = createdTimestamp; }

    public OffsetDateTime getUpdatedTimestamp() { return updatedTimestamp; }
    public void setUpdatedTimestamp(OffsetDateTime updatedTimestamp) { this.updatedTimestamp = updatedTimestamp; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getSortCode() { return sortCode; }
    public void setSortCode(String sortCode) { this.sortCode = sortCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // ----- Equality (by id) -------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account a)) return false;
        return Objects.equals(id, a.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
