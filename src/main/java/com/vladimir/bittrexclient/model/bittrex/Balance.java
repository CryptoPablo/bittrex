package com.vladimir.bittrexclient.model.bittrex;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class Balance {
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("Balance")
    private BigDecimal balance;
    @JsonProperty("Available")
    private BigDecimal available;
    @JsonProperty("Pending")
    private BigDecimal pending;
    @JsonProperty("CryptoAddress")
    private String cryptoAddress;
    @JsonProperty("Requested")
    private Boolean requested;
    @JsonProperty("Uuid")
    private String uuid;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public BigDecimal getPending() {
        return pending;
    }

    public void setPending(BigDecimal pending) {
        this.pending = pending;
    }

    public String getCryptoAddress() {
        return cryptoAddress;
    }

    public void setCryptoAddress(String cryptoAddress) {
        this.cryptoAddress = cryptoAddress;
    }

    public Boolean getRequested() {
        return requested;
    }

    public void setRequested(Boolean requested) {
        this.requested = requested;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "currency='" + currency + '\'' +
                ", balance=" + balance +
                ", available=" + available +
                ", pending=" + pending +
                ", cryptoAddress='" + cryptoAddress + '\'' +
                ", requested=" + requested +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
