package fr.arolla;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
          calculateBalance = getCalculateBalance(calculateBalance, transaction);
      }
        return calculateBalance;
    }

    private static BigDecimal getCalculateBalance(BigDecimal calculateBalance, Transaction transaction) {
        if(isDeposit(transaction)){
            calculateBalance = calculateBalance.add(transaction.amount());
        }
        if(isWithdraw(transaction)){
            calculateBalance = calculateBalance.subtract(transaction.amount());
        }
        return calculateBalance;
    }

    private static boolean isWithdraw(Transaction transaction) {
        return transaction.type().equals(TransactionType.WITHDRAW);
    }

    private static boolean isDeposit(Transaction transaction) {
        return transaction.type().equals(TransactionType.DEPOSIT);
    }

    public void withdraw(BigDecimal withdrawedAmount, LocalDate date) {
        BigDecimal balance = getBalance();
        if(withdrawedAmount.compareTo(balance) >0)

            throw new NotEnoughMoneyException("you don't have enough money to withdraw!");

        transactions.add(new Transaction(withdrawedAmount,date,TransactionType.WITHDRAW));

    }

    public BigDecimal getBalanceWithSpeceficDate(LocalDate referenceDate) {
        BigDecimal calculateBalance = initBalance;
        for(Transaction transaction: transactions){
            if(transaction.date().isEqual(referenceDate)){
                calculateBalance = getCalculateBalance(calculateBalance, transaction);
            }
        }
        return calculateBalance;
    }
    public void printTransactions(){
        transactions.forEach(System.out::println);
        }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount that)) return false;
        return iban.equals(that.iban) && initBalance.equals(that.initBalance) && transactions.equals(that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, initBalance, transactions);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "iban='" + iban + '\'' +
                ", initBalance=" + initBalance +
                ", transactions=" + transactions +
                '}';
    }
}
