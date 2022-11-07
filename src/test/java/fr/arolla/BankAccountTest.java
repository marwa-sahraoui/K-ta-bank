package fr.arolla;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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


    @ParameterizedTest
    @CsvSource({"50,150", "100,200", "200,300"})
    void depositAmount_should_generate_theExpectedAmount(BigDecimal amount, BigDecimal expectedAmount){
        BankAccount bankAccount = new BankAccount("ABC",BigDecimal.valueOf(100));
        bankAccount.deposit(amount,LocalDate.of(2001,11,10));
        assertThat(bankAccount.getBalance()).isEqualTo(expectedAmount);
    }
}
