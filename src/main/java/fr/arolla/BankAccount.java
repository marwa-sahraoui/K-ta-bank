package fr.arolla;

import java.math.BigDecimal;

public class BankAccount {
    private String iban;
    private BigDecimal initBalance;

    public BankAccount(String iban, BigDecimal initBalance) {
        this.iban = iban;
        this.initBalance = initBalance;
    }

    public void deposit(BigDecimal depositAmount) {
    }

    public BigDecimal getBalance() {
        return BigDecimal.valueOf(50);
    }
}
