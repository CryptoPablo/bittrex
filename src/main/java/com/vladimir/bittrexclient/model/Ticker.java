package com.vladimir.bittrexclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticker {

    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal last;

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "bid=" + bid +
                ", ask=" + ask +
                ", last=" + last +
                '}';
    }
}
