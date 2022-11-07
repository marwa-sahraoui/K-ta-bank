package fr.arolla;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountTest {
    @Test
    public void should_retrun_50_when_depositAmount_50_for_initBalance_with0(){
    //GIVEN
        BankAccount bankAccount = new BankAccount("ABC",BigDecimal.valueOf(0));
    //WHEN
        bankAccount.deposit(BigDecimal.valueOf(50));
    //THEN
        assertThat(bankAccount.getBalance()).isEqualTo(BigDecimal.valueOf(50));
    }


}
