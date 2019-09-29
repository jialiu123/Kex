package com.moba.emums.orderInfo;

/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */
//订单类型

public enum OrderType {
    ORDER_TYPE("消费品订单", 10);

    public static String getShowName(int index) {
        for (OrderType c : OrderType.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private OrderType(String showName, int index) {
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