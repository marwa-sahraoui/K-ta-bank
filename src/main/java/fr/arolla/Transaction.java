package fr.arolla;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private BigDecimal amount;
    private LocalDate date;
    private TransactionType type;

    public Transaction(BigDecimal amount, LocalDate date, TransactionType type) {
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }
}
