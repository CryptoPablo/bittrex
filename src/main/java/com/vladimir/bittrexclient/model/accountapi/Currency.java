package com.vladimir.bittrexclient.model.accountapi;

import java.math.BigDecimal;


public class Currency {

    private String currency;
    private String currencyLong;
    private Integer minConfirmation;
    private BigDecimal txFee;
    private Boolean isActive;
    private Boolean isRestricted;
    private String coinType;
    private String baseAddress;
    private String notice;


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyLong() {
        return currencyLong;
    }

    public void setCurrencyLong(String currencyLong) {
        this.currencyLong = currencyLong;
    }

    public Integer getMinConfirmation() {
        return minConfirmation;
    }

    public void setMinConfirmation(Integer minConfirmation) {
        this.minConfirmation = minConfirmation;
    }

    public BigDecimal getTxFee() {
        return txFee;
    }

    public void setTxFee(BigDecimal txFee) {
        this.txFee = txFee;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getRestricted() {
        return isRestricted;
    }

    public void setRestricted(Boolean restricted) {
        isRestricted = restricted;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currency='" + currency + '\'' +
                ", currencyLong='" + currencyLong + '\'' +
                ", minConfirmation=" + minConfirmation +
                ", txFee=" + txFee +
                ", isActive=" + isActive +
                ", isRestricted=" + isRestricted +
                ", coinType='" + coinType + '\'' +
                ", baseAddress='" + baseAddress + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
