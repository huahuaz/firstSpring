package com.yc.biz;

import com.yc.bean.Resorder;
import com.yc.vo.CartItem;

import java.util.Map;

public interface ResorderBiz {

    /**
     * 下订
     * @param resorder:  订单信息
     * @param shopCart:  购物车，对应订单项
     */
    public Integer completeOrder(Resorder resorder, Map<Integer, CartItem> shopCart);
}
