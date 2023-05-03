package org.geekhub.vitalii.dto;

import java.math.BigDecimal;

public class StockInPortfolioDTO {

    private String symbol;
    private String name;
    private BigDecimal amount;
    private BigDecimal price;
    private BigDecimal value;
    private BigDecimal changeInPercent;
    private String type;

    public StockInPortfolioDTO() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getChangeInPercent() {
        return changeInPercent;
    }

    public String getType() {
        return type;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setChangeInPercent(BigDecimal changeInPercent) {
        this.changeInPercent = changeInPercent;
    }

    public void setType(String type) {
        this.type = type;
    }
}
