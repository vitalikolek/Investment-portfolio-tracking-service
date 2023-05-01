package org.geekhub.vitalii.dto;

public class ExcelCustomerStockDTO {

    private String symbol;
    private String name;
    private String type;
    private double amount;
    private double value;

    public ExcelCustomerStockDTO(String symbol, String name, String type, double amount, double value) {
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
