package org.geekhub.vitalii.dto;

public class SearchUserDTO {

    private String username;
    private Integer crypto;
    private Integer currency;
    private Integer share;

    public SearchUserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public Integer getCrypto() {
        return crypto;
    }

    public Integer getCurrency() {
        return currency;
    }

    public Integer getShare() {
        return share;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCrypto(Integer crypto) {
        this.crypto = crypto;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public void setShare(Integer share) {
        this.share = share;
    }
}
