package fr.arolla;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(BigDecimal amount,LocalDate date,TransactionType type) {

}
