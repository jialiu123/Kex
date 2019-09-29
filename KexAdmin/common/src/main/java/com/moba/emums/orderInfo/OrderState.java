package com.moba.emums.orderInfo;

/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */
//订单状态

public enum OrderState {
    ORDER_STATE_X10("已取消", -10),

    ORDER_STATE_0("已预订", 0),

    ORDER_STATE_10("已支付", 10),

    ORDER_STATE_20("已发货", 20),

    ORDER_STATE_40("确认收货", 40),

    ORDER_STATE_50("已完成", 50),

    ORDER_STATE_90("关闭", 90);

    public static String getShowName(int index) {
        for (OrderState c : OrderState.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }


    private OrderState(String showName, int index) {
        this.showName = showName;
        this.index = index;
    }

    private String showName;

    private int index;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}