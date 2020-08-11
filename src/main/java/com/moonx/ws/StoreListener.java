package com.moonx.ws;

import com.moonx.domain.UserAsset;
import com.moonx.domain.UserFill;
import com.moonx.domain.UserOrder;
import com.moonx.domain.UserPosition;

public interface StoreListener {


    default void userAsset(UserAsset userAsset){

    }
    default void userFill(UserFill fill){

    }
    default void userPosition(UserPosition position){

    }

    default void userOrder(UserOrder order){

    }
}
