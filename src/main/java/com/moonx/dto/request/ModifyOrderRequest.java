package com.moonx.dto.request;

import com.moonx.enums.TimeInForce;
import com.moonx.enums.TradeCoinFlag;

import java.math.BigDecimal;

/**
 * This message allows a client to modify an already submitted and accepted order. A modify order response message will be sent
 * to indicate whether the modify order request has been accepted or rejected by the exchange. If in-flight mitigation is set to
 * true then the request will be rejected if the modify order quantity is not greater than any partial filled quantity for this order.
 * Modifying an order will always cancel and replace the original order, with the exchange order ID remaining the same.
 */

public class ModifyOrderRequest {

    private long exchangeOrderId;

    private TradeCoinFlag tradeCoinFlag;

    private BigDecimal price;

    private BigDecimal amount;

    private Boolean inflightMitigation;

    private TimeInForce timeInForce;

    public ModifyOrderRequest() {
    }

    public ModifyOrderRequest(long exchangeOrderId, TradeCoinFlag tradeCoinFlag, BigDecimal price, BigDecimal amount, Boolean inflightMitigation, TimeInForce timeInForce) {
        this.exchangeOrderId = exchangeOrderId;
        this.tradeCoinFlag = tradeCoinFlag;
        this.price = price;
        this.amount = amount;
        this.inflightMitigation = inflightMitigation;
        this.timeInForce = timeInForce;
    }

    public long getExchangeOrderId() {
        return exchangeOrderId;
    }

    public void setExchangeOrderId(long exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
    }

    public TradeCoinFlag getTradeCoinFlag() {
        return tradeCoinFlag;
    }

    public void setTradeCoinFlag(TradeCoinFlag tradeCoinFlag) {
        this.tradeCoinFlag = tradeCoinFlag;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getInflightMitigation() {
        return inflightMitigation;
    }

    public void setInflightMitigation(Boolean inflightMitigation) {
        this.inflightMitigation = inflightMitigation;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    @Override
    public String toString() {
        return "ModifyOrderRequest{" +
                "exchangeOrderId=" + exchangeOrderId +
                ", tradeCoinFlag=" + tradeCoinFlag +
                ", price=" + price +
                ", amount=" + amount +
                ", inflightMitigation=" + inflightMitigation +
                ", timeInForce=" + timeInForce +
                '}';
    }

}
