package org.example.account.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransactionDto {
    public String id;
    public String type;
    public BigDecimal amount;
    public String currency;
    public BigDecimal balanceAfter;
    public String description;
    public OffsetDateTime createdAt;
}