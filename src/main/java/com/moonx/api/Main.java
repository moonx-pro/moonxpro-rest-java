package com.moonx.api;

import com.alibaba.fastjson.JSONObject;
import com.moonx.domain.UserAsset;
import com.moonx.domain.UserFill;
import com.moonx.domain.UserOrder;
import com.moonx.domain.UserPosition;
import com.moonx.dto.request.*;
import com.moonx.enums.ExecutionType;
import com.moonx.enums.OrderSide;
import com.moonx.enums.OrderType;
import com.moonx.enums.TimeInForce;
import com.moonx.ws.StoreListener;
import com.moonx.ws.StreamKey;
import com.moonx.ws.Subscription;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    static final String BUSINESS_NO = "Change_Here";
    static final String API_SECRET = "Change_Here";

    public static void main(String[] args) {
        try {
            ApiClient client = new ApiClient(BUSINESS_NO, API_SECRET);
            publicApiTest();
            //futuresApiTest(client);
            //spotApiTest(client);
            websocketTest(client);
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

    static void futuresApiTest(ApiClient client) throws IOException {


        /************************* New Order ************************/
        OrderRequest order = new OrderRequest();
        order.setSymbol("BTC_USD");
        order.setQuantity(new BigDecimal("100"));
        order.setPrice(new BigDecimal("7000.5"));
        order.setOrderType(OrderType.LIMIT);
        order.setOrderSide(OrderSide.BUY);
        order.setTimeInForce(TimeInForce.GTC);
        order.setExecutionType(ExecutionType.REGULAR);
        // order.setTriggerOn(TriggerOn.LAST_TRADED_PRICE);
        // order.setTriggerPrice(new BigDecimal("7000"));
        // order.setDisclosedQuantity(new BigDecimal("100"));
        // printResponse(client.createNewOrder(order));

        /************************* Modify Order ************************/
        OrderRequest modify = new OrderRequest();
        modify.setOrderNo(1234);
        modify.setPrice(new BigDecimal("8000"));
        // modify.setQuantity(new BigDecimal("50"));
        // modify.setTriggerPrice(new BigDecimal("8000"));
        //printResponse(client.modifyOrder(modify));


        /************************* Cancel Order ************************/
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
        cancelOrderRequest.setOrderNo(4050);
        // printResponse(client.cancelOrder(cancelOrderRequest));


        /************************* Modify Leverage ************************/
        ModifyLeverageDto modifyLeverageDto = new ModifyLeverageDto();
        modifyLeverageDto.setSymbol("BTC_USD");
        modifyLeverageDto.setLeverage(new BigDecimal("2"));
        // printResponse(client.modifyLeverage(modifyLeverageDto));


        /************************ Get Open Order Details *************************/
        OrderQueryRequest orderQuery = new OrderQueryRequest();
        orderQuery.setOrderNo(4050);
        // printResponse(client.getOrderDetails(orderQuery)); // Success response will be returned only if order is in Open state.


        OrderQueryRequest query = new OrderQueryRequest();
        /************************ Get Open Orders ************************/
        query.setSymbol("BTC_USD");
        // printResponse(client.getOpenOrders(query));

        /************************ Get Stop Orders ************************/
        query.setSymbol("BTC_USD");
        // printResponse(client.getStops(query));


        /************************ Get Fills ************************/
        query.setSymbol("BTC_USD");
        // printResponse(client.getFills(query));


        /************************ Get Order History ************************/
        query.setSymbol("BTC_USD");
        // printResponse(client.getOrderHistory(query));


        /************************* Assets Query **************************/
        AssetQueryRequest assetQuery = new AssetQueryRequest();
        assetQuery.setAssetCode("BTC");
        // printResponse(client.assetQuery(assetQuery));


        /************************* Get Future Account Info **************************/
        AssetQueryRequest futureInfoQuery = new AssetQueryRequest();
        futureInfoQuery.setSymbol("BTC_USD");
        //printResponse(client.getFutureInfo(futureInfoQuery));


        /**************************** Get Symbol Config *******************************/
        SymbolQueryRequest symbolQuery = new SymbolQueryRequest();
        symbolQuery.setSymbol("BTC_USD");
        // printResponse(client.symbols(symbolQuery));
    }



    static void spotApiTest(ApiClient client) throws IOException {
        /************************* New Order ************************/
        OrderRequest order = new OrderRequest();
        order.setSymbol("BTC_ETH");
        order.setQuantity(new BigDecimal("1"));
        order.setPrice(new BigDecimal("0.0231"));
        order.setOrderType(OrderType.LIMIT);
        order.setOrderSide(OrderSide.BUY);
        order.setTimeInForce(TimeInForce.GTC);
        // printResponse(client.createNewOrder(order));

        /************************* Modify Order ************************/
        OrderRequest modify = new OrderRequest();
        modify.setOrderNo(1234);
        modify.setPrice(new BigDecimal("8000"));
        // modify.setQuantity(new BigDecimal("50"));
        // modify.setTriggerPrice(new BigDecimal("8000"));
        // printResponse(client.modifyOrder(modify));


        /************************* Cancel Order ************************/
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
        cancelOrderRequest.setOrderNo(4050);
        // printResponse(client.cancelOrder(cancelOrderRequest));



        /************************ Get Open Order Details *************************/
        OrderQueryRequest orderQuery = new OrderQueryRequest();
        orderQuery.setOrderNo(4050);
        // printResponse(client.getOrderDetails(orderQuery)); // Success response will be returned only if order is in Open state.


        OrderQueryRequest query = new OrderQueryRequest();
        /************************ Get Open Orders ************************/
        query.setSymbol("BTC_USD");
        // printResponse(client.getOpenOrders(query));

        /************************ Get Stop Orders ************************/
        query.setSymbol("BTC_USD");
        // printResponse(client.getStops(query));


        /************************ Get Fills ************************/
        query.setSymbol("BTC_USD");
        // printResponse(client.getFills(query));


        /************************ Get Order History ************************/
        query.setSymbol("BTC_USD");
        // printResponse(client.getOrderHistory(query));


        /************************* Assets Query **************************/
        AssetQueryRequest assetQuery = new AssetQueryRequest();
        assetQuery.setAssetCode("BTC");
        // printResponse(client.assetQuery(assetQuery));



        /**************************** Get Symbol Config *******************************/
        SymbolQueryRequest symbolQuery = new SymbolQueryRequest();
        symbolQuery.setSymbol("BTC_USD");
        // printResponse(client.symbols(symbolQuery));
    }


    static void websocketTest(ApiClient client) throws IOException {
        /************************* Websocket **************************/
        client.subscribe(new Subscription().streamKey(StreamKey.parse("stack@btc_eth")).throttleMillis(2000));
        client.subscribe(new Subscription().streamKey(StreamKey.parse("trade@usdt_btc")));
        client.subscribe(new Subscription().streamKey(StreamKey.parse("stat")).put("symbol", "*_ETH").throttleMillis(5000));
        client.subscribe(new Subscription().streamKey(StreamKey.parse("user-future")));
        client.subscribe(new Subscription().streamKey(StreamKey.parse("user-order")));
        client.subscribe(new Subscription().streamKey(StreamKey.parse("user-trade")));
        client.subscribe(new Subscription().streamKey(StreamKey.parse("user-asset")));


        StoreListener sl = new StoreListener() {
            @Override
            public void userAsset(UserAsset userAsset) {
                System.out.println("Change in user asset::" + userAsset.toString());
            }

            @Override
            public void userOrder(UserOrder order) {
                System.out.println("Change in user order::" + order.toString());
            }

            @Override
            public void userPosition(UserPosition position) {
                System.out.println("Change in user position::" + position.toString());
            }

            @Override
            public void userFill(UserFill fill) {
                System.out.println("New fill notification::" + fill.toString());
            }
        };

        // Add listeners
        client.store().addListener(sl);

        // Remove listeners
        //client.store().removeListener(sl);

        //Un-subscribing stack@btc_usd after 10 seconds.
        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                client.unSubscribe(new Subscription().streamKey(StreamKey.parse("stack@btc_eth")));
            }
        }, 10000L);*/
    }


    static void printResponse(Object obj) {
        System.out.println("Response: ");
        System.out.println(StringEscapeUtils.
                unescapeJava(JSONObject.toJSONString(obj, true)));
        System.out.println();
    }
}
