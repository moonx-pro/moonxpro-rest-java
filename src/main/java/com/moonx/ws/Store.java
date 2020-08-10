package com.moonx.ws;

import com.alibaba.fastjson.JSONObject;
import com.moonx.domain.FillDetails;
import com.moonx.domain.FutureInfo;
import com.moonx.domain.OrderDetails;
import com.moonx.domain.UserAsset;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Store implements StreamListener {

    Map<String, FutureInfo> futureInfos = new ConcurrentHashMap<>();
    Map<String, FillDetails> fills = new ConcurrentHashMap<>();
    Map<String, OrderDetails> orders = new ConcurrentHashMap<>();
    Map<String, UserAsset> userAsset = new ConcurrentHashMap<>();

    @Override
    public void delta(Message msg) {
        switch (msg.streamKey().getFlavour()) {
            case OrderStack:
                break;
            case Trade:
                break;
            case Stat:
                break;
            case Index:
                break;
            case UserOrder:
                Optional.ofNullable(msg.<JSONObject>data())
                        .map(o -> o.toJavaObject(OrderDetails.class))
                        .ifPresent(o -> orders.put(o.getSymbol(), o));
                break;
            case UserTrade:
                Optional.ofNullable(msg.<JSONObject>data())
                        .map(o -> o.toJavaObject(FillDetails.class))
                        .ifPresent(o -> fills.put(o.getSymbol(), o));
                break;
            case FutureInfo:
                Optional.ofNullable(msg.<JSONObject>data())
                        .map(o -> o.toJavaObject(FutureInfo.class))
                        .ifPresent(o -> futureInfos.put(o.getSymbol(), o));
                break;
            case UserAsset:
                Optional.ofNullable(msg.<JSONObject>data())
                        .map(o -> o.toJavaObject(UserAsset.class))
                        .ifPresent(o -> userAsset.put(o.getAssetCode(), o));
                break;
        }
    }

    @Override
    public void snapshot(Message msg) {
        switch (msg.streamKey().getFlavour()) {
            case OrderStack:
                break;
            case Trade:
                break;
            case Kline:
                break;
            case Stat:
                break;
            case Index:
                break;
            case UserOrder:
                orders.clear();
                Optional.ofNullable(msg.<JSONObject>data())
                        .map(o -> o.getJSONArray("list"))
                        .ifPresent(o -> o.stream()
                                .map(x -> (JSONObject) x)
                                .map(x -> x.toJavaObject(OrderDetails.class))
                                .forEach(x -> orders.put(x.getSymbol(), x))
                        );
                break;
            case UserTrade:
                fills.clear();
                Optional.ofNullable(msg.<JSONObject>data())
                        .map(o -> o.getJSONArray("list"))
                        .ifPresent(o -> o.stream()
                                .map(x -> (JSONObject) x)
                                .map(x -> x.toJavaObject(FillDetails.class))
                                .forEach(x -> fills.put(x.getSymbol(), x))
                        );
                break;
            case FutureInfo:
                futureInfos.clear();
                Optional.ofNullable(msg.<JSONObject>data())
                        .map(o -> o.getJSONArray("list"))
                        .ifPresent(o -> o.stream()
                                .map(x -> (JSONObject) x)
                                .map(x -> x.toJavaObject(FutureInfo.class))
                                .forEach(x -> futureInfos.put(x.getSymbol(), x))
                        );
                break;
            case UserAsset:
                userAsset.clear();
                Optional.ofNullable(msg.<JSONObject>data())
                        .map(o -> o.getJSONArray("list"))
                        .ifPresent(o -> o.stream()
                                .map(x -> (JSONObject) x)
                                .map(x -> x.toJavaObject(UserAsset.class))
                                .forEach(x -> userAsset.put(x.getAssetCode(), x))
                        );
                break;
        }
    }

    @Override
    public void disconnected() {

    }
}
