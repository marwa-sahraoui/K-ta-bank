package fr.arolla;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BankAccountTest {
    @Test
    public void should_retrun_50_when_depositAmount_50_for_initBalance_with0(){
    //GIVEN
        BankAccount bankAccount = new BankAccount("ABC",BigDecimal.valueOf(0));
    //WHEN
        bankAccount.deposit(BigDecimal.valueOf(50), LocalDate.of(2000,10,10));
    //THEN
        assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(50));
    }

    @ParameterizedTest
    @CsvSource({"50,150", "100,200", "200,300"})
    public void depositAmount_should_generate_theExpectedAmount(BigDecimal amount, BigDecimal expectedAmount){
        BankAccount bankAccount = new BankAccount("ABC",BigDecimal.valueOf(100));
        bankAccount.deposit(amount,LocalDate.of(2001,11,10));
        assertThat(bankAccount.getBalance()).isEqualTo(expectedAmount);
    }
    @ParameterizedTest
    @CsvSource({"50,950", "200,800", "500,500"})
     public void withdrawAmount_should_generate_theExpectedAmount(BigDecimal amount, BigDecimal expectedAmount){
        //GIVEN
        BankAccount bankAccount = new BankAccount("ABC",BigDecimal.valueOf(1000));
        //WHEN
        bankAccount.withdraw(amount, LocalDate.of(2000,10,10));
        //THEN
        assertThat(bankAccount.getBalance()).isEqualTo(expectedAmount);
    }
    @Test
    public void should_return50_When_Deposit100_And_Withrawed_50(){
        //GIVEN
        BankAccount bankAccount = new BankAccount("ABC",BigDecimal.valueOf(0));
        //WHEN
        bankAccount.deposit(BigDecimal.valueOf(100), LocalDate.of(2000,10,10));
        bankAccount.withdraw(BigDecimal.valueOf(50), LocalDate.of(2000,10,11));
        //THEN
        assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(50));
    }
    @Test
    public void exceptionThrown_when_withdrawed_amount_is_higher_than_balance(){
        BankAccount bankAccount = new BankAccount("abc", BigDecimal.valueOf(50));
        assertThatThrownBy(()->bankAccount.withdraw(BigDecimal.valueOf(300),LocalDate.of(2007,10,11)))
                .isInstanceOf(NotEnoughMoneyException.class);

        assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(50));
    }
    @Test
    public void should_return_500_When_Deposit500At_SpecifcDate_And_Deposit_200_AtAnotherDate(){
        //GIVEN
        BankAccount bankAccount = new BankAccount("abc",BigDecimal.valueOf(0));
        //WHEN
        bankAccount.deposit(BigDecimal.valueOf(500), LocalDate.of(2000,10,10));
        bankAccount.deposit(BigDecimal.valueOf(200), LocalDate.of(2000,11,11));
        //THEN
        assertThat(bankAccount.getBalanceWithSpeceficDate(LocalDate.of(2000,10,10))).isEqualTo(BigDecimal.valueOf(500));
    }

}
