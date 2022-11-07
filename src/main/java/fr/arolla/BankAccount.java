package fr.arolla;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BankAccount {
    private String iban;
    private BigDecimal initBalance;
    private List<Transaction> transactions;

    public BankAccount(String iban, BigDecimal initBalance) {
        this.iban = iban;
        this.initBalance = initBalance;
        transactions = new ArrayList<>();
    }

    public void deposit(BigDecimal depositAmount, LocalDate date) {
        transactions.add(new Transaction(depositAmount,date,TransactionType.DEPOSIT));
    }

    public BigDecimal getBalance() {
      BigDecimal calculateBalance = initBalance;

      for(Transaction transaction: transactions){
         if(transaction.getType().equals(TransactionType.DEPOSIT)){
             calculateBalance = calculateBalance.add(transaction.getAmount());
         }
          if(transaction.getType().equals(TransactionType.WITHDRAW)){
              calculateBalance = calculateBalance.subtract(transaction.getAmount());
          }
      }
        return calculateBalance;
    }

    public void withdraw(BigDecimal withdrawedAmount, LocalDate date) {
        transactions.add(new Transaction(withdrawedAmount,date,TransactionType.WITHDRAW));
    }
}
