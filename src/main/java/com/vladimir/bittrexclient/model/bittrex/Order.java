package com.vladimir.bittrexclient.model.bittrex;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    @JsonProperty("OrderUuid")
    private String orderUuid;
    @JsonProperty("Exchange")
    private String exchange;
    @JsonProperty("OrderType")
    private String orderType;
    @JsonProperty("Quantity")
    private BigDecimal quantity;
    @JsonProperty("QuantityRemaining")
    private BigDecimal quantityRemaining;
    @JsonProperty("Limit")
    private BigDecimal limit;
    @JsonProperty("CommissionPaid")
    private BigDecimal commissionPaid;
    @JsonProperty("Price")
    private BigDecimal price;
    @JsonProperty("PricePerUnit")
    private BigDecimal pricePerUnit;
    @JsonProperty("Opened")
    private Date opened;
    @JsonProperty("Closed")
    private Date closed;
    @JsonProperty("CancelInitiated")
    private boolean cancelInitiated;
    @JsonProperty("ImmediateOrCancel")
    private boolean immediateOrCancel;
    @JsonProperty("IsConditional")
    private boolean isConditional;

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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public BigDecimal getCommissionPaid() {
        return commissionPaid;
    }

    public void setCommissionPaid(BigDecimal commissionPaid) {
        this.commissionPaid = commissionPaid;
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

    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public boolean isCancelInitiated() {
        return cancelInitiated;
    }

    public void setCancelInitiated(boolean cancelInitiated) {
        this.cancelInitiated = cancelInitiated;
    }

    public boolean isImmediateOrCancel() {
        return immediateOrCancel;
    }

    public void setImmediateOrCancel(boolean immediateOrCancel) {
        this.immediateOrCancel = immediateOrCancel;
    }

    public boolean isConditional() {
        return isConditional;
    }

    public void setConditional(boolean conditional) {
        isConditional = conditional;
    }
}
