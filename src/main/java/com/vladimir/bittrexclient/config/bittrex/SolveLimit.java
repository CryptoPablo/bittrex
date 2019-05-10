package com.vladimir.bittrexclient.config.bittrex;

import java.math.BigDecimal;

public class SolveLimit implements Limit{
    private String ticker;
    private BigDecimal limit;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
