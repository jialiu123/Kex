package com.moba.emums.orderInfo;

/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */
//支付类型
public enum PayType {
    PAY_TYPE_0("线下支付", 0),

    PAY_TYPE_1("拍拍", 1),

    PAY_TYPE_2("银行汇款", 2);

    public static String

    getShowName(int index) {
        for (PayType c : PayType.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private PayType(String showName, int index) {
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