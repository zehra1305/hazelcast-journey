package com.example.banka_eft_kontrolu.dto;

import java.math.BigDecimal;

public class EftDto {
    private String iban;
    private BigDecimal amount;
     public EftDto(String iban, BigDecimal amount) {
         this.iban = iban;
         this.amount = amount;
     }
     public String getIban() {
         return iban;
     }
     public void setIban(String iban) {
         this.iban = iban;
     }
     public BigDecimal getAmount() {
         return amount;
     }
     public void setAmount(BigDecimal amount) {
         this.amount = amount;
     }
     public EftDto() {}
}
