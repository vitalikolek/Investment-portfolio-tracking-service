package org.geekhub.vitalii.dto;

public class StockStatsDTO {

    private String assetType;
    private Long assetCount;
    private Float avgChangeInPercent;
    private Long totalMarketCap;
    private Long totalVolume;

    public StockStatsDTO() {
    }

    public String getAssetType() {
        return assetType;
    }

    public Long getAssetCount() {
        return assetCount;
    }

    public Float getAvgChangeInPercent() {
        return avgChangeInPercent;
    }

    public Long getTotalMarketCap() {
        return totalMarketCap;
    }

    public Long getTotalVolume() {
        return totalVolume;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public void setAssetCount(Long assetCount) {
        this.assetCount = assetCount;
    }

    public void setAvgChangeInPercent(Float avgChangeInPercent) {
        this.avgChangeInPercent = avgChangeInPercent;
    }

    public void setTotalMarketCap(Long totalMarketCap) {
        this.totalMarketCap = totalMarketCap;
    }

    public void setTotalVolume(Long totalVolume) {
        this.totalVolume = totalVolume;
    }
}
