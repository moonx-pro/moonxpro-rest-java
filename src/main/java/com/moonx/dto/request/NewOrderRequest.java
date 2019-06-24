package com.moonx.dto.request;

import com.moonx.enums.TimeInForce;
import com.moonx.enums.TradeCoinFlag;
import com.moonx.enums.TradeCoinType;

public class NewOrderRequest {
    private String symbol;
    private TradeCoinFlag tradeCoinFlag;
    private TradeCoinType tradeCoinType;
    private String price;
    private String amount;
    private TimeInForce timeInForce;

    public NewOrderRequest() {
    }

    public NewOrderRequest(String symbol, TradeCoinFlag tradeCoinFlag, TradeCoinType tradeCoinType, String price, String amount, TimeInForce timeInForce) {
        this.symbol = symbol;
        this.tradeCoinFlag = tradeCoinFlag;
        this.tradeCoinType = tradeCoinType;
        this.price = price;
        this.amount = amount;
        this.timeInForce = timeInForce;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public TradeCoinFlag getTradeCoinFlag() {
        return tradeCoinFlag;
    }

    public void setTradeCoinFlag(TradeCoinFlag tradeCoinFlag) {
        this.tradeCoinFlag = tradeCoinFlag;
    }

    public TradeCoinType getTradeCoinType() {
        return tradeCoinType;
    }

    public void setTradeCoinType(TradeCoinType tradeCoinType) {
        this.tradeCoinType = tradeCoinType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }

    @Override
    public String toString() {
        return "NewOrderRequest{" +
                "symbol='" + symbol + '\'' +
                ", tradeCoinFlag=" + tradeCoinFlag +
                ", tradeCoinType=" + tradeCoinType +
                ", price='" + price + '\'' +
                ", amount='" + amount + '\'' +
                ", timeInForce=" + timeInForce +
                '}';
    }
}
