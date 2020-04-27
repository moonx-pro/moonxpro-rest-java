package com.moonx.ws;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moonx.domain.FutureInfo;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Store implements StreamListener {

    Map<String, FutureInfo> futureInfos = new ConcurrentHashMap<>();

    @Override
    public void delta(Message msg) {
        System.out.println(msg.<Object>data());
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
                break;
            case UserTrade:
                break;
            case FutureInfo:
                Optional.ofNullable(msg.<JSONObject>data())
                        .map(o -> o.toJavaObject(FutureInfo.class))
                        .ifPresent(o -> futureInfos.put(o.getSymbol(), o));
                break;
            case UserAsset:
                break;
        }
    }

    @Override
    public void snapshot(Message msg) {
        System.out.println(msg.<Object>data());
        switch (msg.streamKey().getFlavour()) {
            case OrderStack:
                break;
            case Trade:
                break;
            case Kline:
                break;
            case Stat:
                break;
            case Announce:
                break;
            case Index:
                break;
            case UserOrder:
                break;
            case UserTrade:
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
                break;
        }
    }

    @Override
    public void disconnected() {

    }
}
