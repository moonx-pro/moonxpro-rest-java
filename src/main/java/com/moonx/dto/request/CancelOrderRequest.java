package com.moonx.dto.request;

public class CancelOrderRequest {
    long exchangeOrderId;

    public long getExchangeOrderId() {
        return exchangeOrderId;
    }

    public void setExchangeOrderId(long exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
    }
}
