package com.vladimir.bittrexclient.model.bittrexmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositHistoryEntry {
    @JsonProperty("Id")
    private long id;
    @JsonProperty("Amount")
    private BigDecimal amount;
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("Confirmations")
    private long confirmations;
    @JsonProperty("LastUpdated")
    private LocalDateTime lastUpdated;
    @JsonProperty("TxID")
    private String txId;
    @JsonProperty("CryptoAddress")
    private String cryptoAddress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(long confirmations) {
        this.confirmations = confirmations;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getCryptoAddress() {
        return cryptoAddress;
    }

    public void setCryptoAddress(String cryptoAddress) {
        this.cryptoAddress = cryptoAddress;
    }
}
