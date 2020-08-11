package com.moonx.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private BigDecimal price;
    private BigDecimal quantity;
}
