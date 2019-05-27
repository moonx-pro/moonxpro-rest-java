package com.moonx.api;

import com.alibaba.fastjson.JSONObject;
import com.moonx.dto.request.*;
import com.moonx.dto.response.ApiResponse;
import com.moonx.enums.RequestType;

import java.io.IOException;
import java.util.HashMap;

public class ApiClient {

    final String businessNo;
    final String apiSecret;

    public ApiClient(String businessNo, String apiSecret){
        this.businessNo = businessNo;
        this.apiSecret = apiSecret;
    }

    public ApiResponse<String> createNewOrder(NewOrderRequest newOrderRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.NEW_ORDER, newOrderRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> assetQuery(AssetQueryRequest assetQueryRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.ASSET_QUERY, assetQueryRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> cancelOrder(CancelOrderRequest cancelOrderRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.CANCEL_ORDER, cancelOrderRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> bulkCancel(BulkCancelRequest bulkCancelReq) throws IOException {
        return new ApiUtil().sendRequest(RequestType.CANCEL_BULK, bulkCancelReq, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> orderQuery(OrderQueryRequest orderQueryRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.ORDER_QUERY, orderQueryRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> queryAllOpen(OrderQueryRequest orderQueryRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.ALL_OPEN_ORDERS_QUERY, orderQueryRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> symbols(SymbolQueryRequest symbolQueryRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.SYMBOLS, symbolQueryRequest, this.businessNo, this.apiSecret, String.class);
    }

    public static JSONObject depth(String symbol) throws IOException {
        HashMap params = new HashMap();
        params.put("symbol", symbol);
        return new ApiUtil().sendRequest(RequestType.DEPTH, params);
    }

    public static JSONObject tradeHistory(String symbol) throws IOException {
        HashMap params = new HashMap();
        params.put("symbol", symbol);
        return new ApiUtil().sendRequest(RequestType.TRADE_HISTORY, params);
    }

    public static JSONObject marketTickers() throws IOException {
        return new ApiUtil().sendRequest(RequestType.TICKERS, null);
    }

    public static JSONObject kline(String symbol, long startTime, long endTime, long pageSize, String kline) throws IOException {
        HashMap params = new HashMap();
        params.put("symbol", symbol);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("pageSize", pageSize);
        params.put("kline", kline);
        return new ApiUtil().sendRequest(RequestType.KLINE, params);
    }
}
