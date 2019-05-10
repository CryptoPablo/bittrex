package com.vladimir.bittrexclient.model.bittrex;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawalHistoryEntry {
    @JsonProperty("PaymentUuid")
    private String paymentUuid;
    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("Amount")
    private String amount;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("Opened")
    private Date opened;
    @JsonProperty("Authorized")
    private Boolean authorized;
    @JsonProperty("PendingPayment")
    private Boolean pendingPayment;
    @JsonProperty("TxCost")
    private Double txCost;
    @JsonProperty("TxId")
    private String txId;
    @JsonProperty("Canceled")
    private Boolean canceled;
    @JsonProperty("InvalidAddress")
    private Boolean invalidAddress;

    public String getPaymentUuid() {
        return paymentUuid;
    }

    public void setPaymentUuid(String paymentUuid) {
        this.paymentUuid = paymentUuid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }

    public Boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }

    public Boolean getPendingPayment() {
        return pendingPayment;
    }

    public void setPendingPayment(Boolean pendingPayment) {
        this.pendingPayment = pendingPayment;
    }

    public Double getTxCost() {
        return txCost;
    }

    public void setTxCost(Double txCost) {
        this.txCost = txCost;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Boolean getInvalidAddress() {
        return invalidAddress;
    }

    public void setInvalidAddress(Boolean invalidAddress) {
        this.invalidAddress = invalidAddress;
    }

    @Override
    public String toString() {
        return "WithdrawalHistoryEntry{" +
                "paymentUuid='" + paymentUuid + '\'' +
                ", currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                ", address='" + address + '\'' +
                ", opened=" + opened +
                ", authorized=" + authorized +
                ", pendingPayment=" + pendingPayment +
                ", txCost=" + txCost +
                ", txId='" + txId + '\'' +
                ", canceled=" + canceled +
                ", invalidAddress=" + invalidAddress +
                '}';
    }
}
