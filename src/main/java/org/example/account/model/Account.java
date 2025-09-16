package org.example.account.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity @Table(name = "accounts")
public class Account {
    @Id private String id;
    @Column(name="user_id", nullable=false) private String userId;
    @Column(name="account_type", nullable=false) private String accountType;
    @Column(name="currency", nullable=false, length=3) private String currency;
    @Column(name="balance", nullable=false, precision=19, scale=2) private BigDecimal balance;
    @Column(name="created_at") private OffsetDateTime createdTimestamp;
    @Column(name="updated_at") private OffsetDateTime updatedTimestamp;
    // getters/setters…



    // --- getters & setters (match your User.java pattern) ---

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
}
