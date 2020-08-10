package com.moonx.domain;

import com.moonx.enums.InstrumentType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FillDetails {
    private InstrumentType itype;

    private long id;

    private long userId;

    private String symbol;

    private String side;

    private long orderId;

    private BigDecimal quantity;

    private BigDecimal price;

    private BigDecimal fee;

    private String makerTaker;

    private Date time;

    private boolean selfTrade;

    private Integer statusId;
}
