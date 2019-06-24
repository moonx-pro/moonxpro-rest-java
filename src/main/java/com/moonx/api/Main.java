package com.moonx.api;

import com.alibaba.fastjson.JSONObject;
import com.moonx.dto.request.*;
import com.moonx.enums.TimeInForce;
import com.moonx.enums.TradeCoinFlag;
import com.moonx.enums.TradeCoinType;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    static final String BUSINESS_NO = "BUSINESS_NO";
    static final String API_SECRET = "API_SECRET";

    public static void main(String[] args) {

        try {
            publicApiTest();
            privateApiTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void publicApiTest() throws IOException {

        /**************************** Market Depth *******************************/
        printResponse(ApiClient.depth("BTC_ETH"));


        /********************** Trade History **********************************/
        printResponse(ApiClient.tradeHistory("BTC_ETH"));


        /********************** Market Tickers **********************************/
        printResponse(ApiClient.marketTickers());


        /********************** Kline Query **********************************/
        printResponse(ApiClient.kline("BTC_ETH", 1558956833,1558986833, 300, "30m"));


    }

    static void privateApiTest() throws IOException {
        ApiClient client = new ApiClient(BUSINESS_NO, API_SECRET);


        /**************************** Symbol Request *******************************/
        SymbolQueryRequest symbolQuery = new SymbolQueryRequest();
        symbolQuery.setSymbol("BTC_ETH"); //Optional. List of all available symbols will be returned if not provided or if invalid value is passed
        //printResponse(client.symbols(symbolQuery));


        /**************************Post Asset Query Request************************/
        AssetQueryRequest assetQuery = new AssetQueryRequest();
        assetQuery.setAssetCode("BTC");
        //printResponse(client.assetQuery(assetQuery));


        /************************* Post New Order Request ************************/
        NewOrderRequest order = new NewOrderRequest();
        order.setSymbol("BTC_ETH");
        order.setAmount("0.125");
        order.setPrice("0.03212"); // will be ignored if TradeCoinFlag is MARKET.
        order.setTradeCoinFlag(TradeCoinFlag.FIXED);
        order.setTradeCoinType(TradeCoinType.BUY);
        order.setTimeInForce(TimeInForce.GTC);
        //printResponse(client.createNewOrder(order));

        /************************* Post Modify Order Request ************************/
        ModifyOrderRequest modify = new ModifyOrderRequest();
        modify.setExchangeOrderId(95);
        modify.setTradeCoinFlag(TradeCoinFlag.FIXED);
        modify.setAmount(new BigDecimal("0.125"));
        modify.setPrice(new BigDecimal("0.033"));
        modify.setTimeInForce(TimeInForce.GTC);
        modify.setInflightMitigation(true);
        printResponse(client.createModifyOrder(modify));


        /************************ Post Order Query Request*************************/
        OrderQueryRequest orderQuery = new OrderQueryRequest();
        orderQuery.setExchangeOrderId(42097);
        //printResponse(client.orderQuery(orderQuery));


        /************************ Post Open Order Query Request************************/
        OrderQueryRequest allOpenOrders = new OrderQueryRequest();
        allOpenOrders.setSymbol("BTC_ETH");
        //printResponse(client.queryAllOpen(allOpenOrders));



        /************************* Post Cancel Order Request **************************/
        CancelOrderRequest cancelOrder = new CancelOrderRequest();
        cancelOrder.setExchangeOrderId(1);
        //printResponse(client.cancelOrder(cancelOrder));


        /************************ Post Bulk Cancel Request*****************************/
        BulkCancelRequest bulkCancelRequest = new BulkCancelRequest();
        bulkCancelRequest.setSymbol("BTC_ETH"); // will be ignored if exchangeOrderIds are provided
        bulkCancelRequest.setExchangeOrderIds("0,1,2");
        //printResponse(client.bulkCancel(bulkCancelRequest));

        /************************* Trade Data Request **************************/
        TradeDownloadRequest tradeDownloadRequest = new TradeDownloadRequest();
        tradeDownloadRequest.setFromDate(1560988800000L);
        tradeDownloadRequest.setToDate(1561161600000L);
        tradeDownloadRequest.setSymbols("USDT_BTC,BTC_ETH");
        //printResponse(client.tradeDataAsJSON(tradeDownloadRequest));

        /************************* Trade Data Download **************************/
        TradeDownloadRequest tradeDownloadRequest_ = new TradeDownloadRequest();
        tradeDownloadRequest_.setFromDate(1560988800000L);
        tradeDownloadRequest_.setToDate(1561161600000L);
        tradeDownloadRequest_.setSymbols("USDT_BTC,BTC_ETH");
        //printResponse("File has been downloaded to :: " + client.tradeDataAsXLSX(tradeDownloadRequest_));


    }


    static void printResponse(Object obj) {
        System.out.println("Response: ");
        System.out.println(StringEscapeUtils.
                unescapeJava(JSONObject.toJSONString(obj, true)));
        System.out.println();
    }
}
