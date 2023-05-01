package org.geekhub.vitalii.dto;

import java.math.BigDecimal;

public class AddStockDTO {

    private String symbol;
    private String type;
    private BigDecimal amount;

    public AddStockDTO() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
