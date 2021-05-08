package com.yc.biz.impl;

import com.yc.bean.Resorder;
import com.yc.bean.Resorderitem;
import com.yc.biz.ResorderBiz;
import com.yc.dao.ResorderDao;
import com.yc.dao.ResorderitemDao;
import com.yc.enums.OrderStatusEnum;
import com.yc.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Map;

@Service
@Transactional
public class ResorderBizImpl implements ResorderBiz {

    @Autowired
    private ResorderDao resorderDao;
    @Autowired
    private ResorderitemDao resorderitemDao;

    @Override
    public Integer completeOrder(Resorder resorder, Map<Integer, CartItem> shopCart) {
        resorder.setStatus(OrderStatusEnum.NEW.getCode());
        resorder.setOrdertime(new Timestamp((System.currentTimeMillis())));
        resorder.setDeliverytime(resorder.getDeliveryTime());
        Resorder orderResult = resorderDao.save(resorder);  //订单号
        if (shopCart != null && shopCart.size() > 0) {
            for (Map.Entry<Integer, CartItem> entry : shopCart.entrySet()) {
                Resorderitem ri = new Resorderitem();
                ri.setRoid(orderResult.getRoid());  // 订单编号
                ri.setNum(entry.getValue().getNum());
                ri.setFid(entry.getKey());
                ri.setDealprice(entry.getValue().getFood().getRealprice());
                resorderitemDao.save(ri);
            }
        }
        return orderResult.getRoid();
    }
}
