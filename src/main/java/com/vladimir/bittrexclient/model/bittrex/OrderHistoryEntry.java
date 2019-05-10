package com.vladimir.bittrexclient.model.bittrex;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class OrderHistoryEntry {
    @JsonProperty("OrderUuid")
    private String orderUuid;
    @JsonProperty("Exchange")
    private String exchange;
    @JsonProperty("Timestamp")
    private Date timeSatamp;
    @JsonProperty("OrderType")
    private String orderType;
    @JsonProperty("Limit")
    private BigDecimal limit;
    @JsonProperty("Quantity")
    private BigDecimal quantity;
    @JsonProperty("QuantityRemaining")
    private BigDecimal quantityRemaining;
    @JsonProperty("Commission")
    private BigDecimal commission;
    @JsonProperty("Price")
    private BigDecimal price;
    @JsonProperty("PricePerUnit")
    private BigDecimal pircePerUnit;
    @JsonProperty("IsConditional")
    private boolean isConditional;
    @JsonProperty("ImmediateOrCancel")
    private boolean immediateOrCancel;

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

    public Date getTimeSatamp() {
        return timeSatamp;
    }

    public void setTimeSatamp(Date timeSatamp) {
        this.timeSatamp = timeSatamp;
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

    public BigDecimal getPircePerUnit() {
        return pircePerUnit;
    }

    public void setPircePerUnit(BigDecimal pircePerUnit) {
        this.pircePerUnit = pircePerUnit;
    }

    public boolean isConditional() {
        return isConditional;
    }

    public void setConditional(boolean conditional) {
        isConditional = conditional;
    }

    public boolean isImmediateOrCancel() {
        return immediateOrCancel;
    }

    public void setImmediateOrCancel(boolean immediateOrCancel) {
        this.immediateOrCancel = immediateOrCancel;
    }
}
