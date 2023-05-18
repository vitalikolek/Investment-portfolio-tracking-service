package org.geekhub.vitalii.dto;

import java.math.BigDecimal;

public class UserStockDTO {

    private String symbol;
    private BigDecimal amount;

    public UserStockDTO() {
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
