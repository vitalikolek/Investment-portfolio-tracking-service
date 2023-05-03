package org.geekhub.vitalii.dto;

import java.math.BigDecimal;

public class CurrencyDTO {

    private String symbol;
    private String name;
    private BigDecimal price;
    private BigDecimal dayHigh;
    private BigDecimal dayLow;
    private BigDecimal change;
    private BigDecimal changeInPercent;

    public CurrencyDTO() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDayHigh() {
        return dayHigh;
    }

    public BigDecimal getDayLow() {
        return dayLow;
    }

    public BigDecimal getChange() {
        return change;
    }

    public BigDecimal getChangeInPercent() {
        return changeInPercent;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDayHigh(BigDecimal dayHigh) {
        this.dayHigh = dayHigh;
    }

    public void setDayLow(BigDecimal dayLow) {
        this.dayLow = dayLow;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public void setChangeInPercent(BigDecimal changeInPercent) {
        this.changeInPercent = changeInPercent;
    }
}
