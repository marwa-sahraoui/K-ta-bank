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
         if(isDeposit(transaction)){
             calculateBalance = calculateBalance.add(transaction.getAmount());
         }
          if(isWithdraw(transaction)){
              calculateBalance = calculateBalance.subtract(transaction.getAmount());
          }
      }
        return calculateBalance;
    }

    private static boolean isWithdraw(Transaction transaction) {
        return transaction.getType().equals(TransactionType.WITHDRAW);
    }

    private static boolean isDeposit(Transaction transaction) {
        return transaction.getType().equals(TransactionType.DEPOSIT);
    }

    public void withdraw(BigDecimal withdrawedAmount, LocalDate date) {
        BigDecimal balance = getBalance();
        if(withdrawedAmount.compareTo(balance) >0)

            throw new NotEnoughMoneyException("you don't have enough money to withdraw!");

        transactions.add(new Transaction(withdrawedAmount,date,TransactionType.WITHDRAW));

    }
}
