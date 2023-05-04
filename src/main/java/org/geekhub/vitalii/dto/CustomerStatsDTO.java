package org.geekhub.vitalii.dto;

public class CustomerStatsDTO {

    private Long cryptocurrencyValue;
    private Long shareValue;
    private Long currencyValue;
    private Long totalValue;
    private Integer stockCount;

    public CustomerStatsDTO() {
    }

    public Long getCryptocurrencyValue() {
        return cryptocurrencyValue;
    }

    public Long getShareValue() {
        return shareValue;
    }

    public Long getCurrencyValue() {
        return currencyValue;
    }

    public Long getTotalValue() {
        return totalValue;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setCryptocurrencyValue(Long cryptocurrencyValue) {
        this.cryptocurrencyValue = cryptocurrencyValue;
    }

    public void setShareValue(Long shareValue) {
        this.shareValue = shareValue;
    }

    public void setCurrencyValue(Long currencyValue) {
        this.currencyValue = currencyValue;
    }

    public void setTotalValue(Long totalValue) {
        this.totalValue = totalValue;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }
}
