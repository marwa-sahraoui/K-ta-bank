package fr.arolla;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void should_retrun_100_when_depositAmount_100_for_initBalance_with0(){
        //GIVEN
        BankAccount bankAccount = new BankAccount("ABC",BigDecimal.valueOf(0));
        //WHEN
        bankAccount.deposit(BigDecimal.valueOf(100),LocalDate.of(2000,11,10));
        //THEN
        assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(100));
    }
    @Test
    public void should_retrun_120_when_depositAmount_100_for_initBalance_with20(){
        //GIVEN
        BankAccount bankAccount = new BankAccount("ABC",BigDecimal.valueOf(20));
        //WHEN
        bankAccount.deposit(BigDecimal.valueOf(100),LocalDate.of(2001,11,10));
        //THEN
        assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(120));
    }

}
