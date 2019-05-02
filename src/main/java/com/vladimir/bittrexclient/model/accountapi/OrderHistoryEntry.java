package com.vladimir.bittrexclient.model.accountapi;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class OrderHistoryEntry {
    private String orderUuid;
    private String exchange;
    private LocalDateTime timeStamp;
    private String orderType;
    private BigDecimal limit;
    private BigDecimal quantity;
    private BigDecimal quantityRemaining;
    private BigDecimal commission;
    private BigDecimal price;
    private BigDecimal pricePerUnit;
    private Boolean isConditional;
    private Boolean immediateOrCancel;


    public String getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(String orderUuid) {
        this.orderUuid = orderUuid;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantityRemaining() {
        return quantityRemaining;
    }

    public void setQuantityRemaining(BigDecimal quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Boolean getIsConditional() {
        return isConditional;
    }

    public void setIsConditional(Boolean conditional) {
        isConditional = conditional;
    }


    public Boolean getImmediateOrCancel() {
        return immediateOrCancel;
    }

    public void setImmediateOrCancel(Boolean immediateOrCancel) {
        this.immediateOrCancel = immediateOrCancel;
    }

    public Boolean getConditional() {
        return isConditional;
    }

    public void setConditional(Boolean conditional) {
        isConditional = conditional;
    }


}
