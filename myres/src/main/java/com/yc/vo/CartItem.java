package com.yc.vo;

import com.yc.bean.Resfood;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 3626349905082107042L;

    private Resfood food;
    private int num;
    private BigDecimal smallCount;

    /**
     * 计算当前这个Cart中此商品的小计
     * @return
     */
    public BigDecimal getSmallCount(){
        this.smallCount = food.getRealprice().multiply(new BigDecimal(num));
        return smallCount;
    }

    public Resfood getFood() {
        return food;
    }

    public void setFood(Resfood food) {
        this.food = food;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
