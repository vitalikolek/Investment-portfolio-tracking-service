package org.geekhub.vitalii.dto;

import java.math.BigDecimal;

public class StockDTO {

    private String symbol;
    private String name;
    private String type;
    private BigDecimal price;
    private BigDecimal dayHigh;
    private BigDecimal dayLow;
    private BigDecimal change;
    private BigDecimal changeInPercent;
    private BigDecimal marketCap;
    private Long volume;
    private BigDecimal balance;
    private BigDecimal value;

    public StockDTO() {
    }

    public StockDTO(String symbol, String name, BigDecimal price, BigDecimal dayHigh, BigDecimal dayLow, BigDecimal change, BigDecimal changeInPercent, BigDecimal marketCap, Long volume) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.dayHigh = dayHigh;
        this.dayLow = dayLow;
        this.change = change;
        this.changeInPercent = changeInPercent;
        this.marketCap = marketCap;
        this.volume = volume;
    }

    public StockDTO(String symbol, String name, BigDecimal price, BigDecimal change, BigDecimal changeInPercent, BigDecimal marketCap, Long volume) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.change = change;
        this.changeInPercent = changeInPercent;
        this.marketCap = marketCap;
        this.volume = volume;
    }

    public StockDTO(String symbol, String name, BigDecimal price, BigDecimal change, BigDecimal changeInPercent) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.change = change;
        this.changeInPercent = changeInPercent;
    }

    public StockDTO(String symbol, String name, String type, BigDecimal price, BigDecimal changeInPercent, BigDecimal balance, BigDecimal value) {
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.price = price;
        this.changeInPercent = changeInPercent;
        this.balance = balance;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public Long getVolume() {
        return volume;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
