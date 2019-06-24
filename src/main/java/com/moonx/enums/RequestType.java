package com.moonx.enums;

import static com.moonx.enums.ApiType.PRIVATE;
import static com.moonx.enums.ApiType.PUBLIC;

public enum RequestType {

    TRADE_HISTORY("market/history/trade", PUBLIC),
    TICKERS("market/tickers", PUBLIC),
    DEPTH("market/depth", PUBLIC),
    KLINE("dataload/kline-query/pages", PUBLIC),
    ASSET_QUERY("exchangeApi/api/v1/asset", PRIVATE),
    NEW_ORDER("exchangeApi/api/v1/order/submit", PRIVATE),
    MODIFY_ORDER("exchangeApi/api/v1/order/modify", PRIVATE),
    ORDER_QUERY("exchangeApi/api/v1/order/query", PRIVATE),
    ALL_OPEN_ORDERS_QUERY("exchangeApi/api/v1/order/query-all-open", PRIVATE),
    CANCEL_ORDER("exchangeApi/api/v1/order/cancel", PRIVATE),
    CANCEL_BULK("exchangeApi/api/v1/order/cancel-bulk", PRIVATE),
    SYMBOLS("exchangeApi/api/v1/symbols/symbol-config", PRIVATE),
    TRADE_DOWNLOAD_JSON("exchangeApi/api/v1/trades/json", PRIVATE),
    TRADE_DOWNLOAD_XLSX("exchangeApi/api/v1/trades/xlsx", PRIVATE);


    public String uri;
    public ApiType apiType;

    RequestType(String uri, ApiType apiType){
        this.uri = uri;
        this.apiType = apiType;
    }
}
