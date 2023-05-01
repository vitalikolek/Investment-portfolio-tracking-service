package org.geekhub.vitalii.dto;

import java.math.BigDecimal;

public class UserStocksDTO {

    private String symbol;
    private BigDecimal amount;
    private String type;

    public UserStocksDTO() {
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }
}
