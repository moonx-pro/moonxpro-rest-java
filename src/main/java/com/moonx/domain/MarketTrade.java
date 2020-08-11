package com.moonx.domain;

import com.moonx.enums.OrderSide;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketTrade {
    private BigDecimal price;
    private BigDecimal num; //volume
    private long time;
    private OrderSide type;




}
