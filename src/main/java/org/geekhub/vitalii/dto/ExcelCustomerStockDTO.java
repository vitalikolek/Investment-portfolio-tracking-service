package org.geekhub.vitalii.dto;

public class ExcelCustomerStockDTO {

    private String symbol;
    private String name;
    private double amount;
    private double price;
    private double value;

    public ExcelCustomerStockDTO() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }


    public double getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public double getValue() {
        return value;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
