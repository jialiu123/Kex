package com.moba.emums.orderInfo;

/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */
//支付平台

public enum PayPlatform {
    PAY_PLATFORM_10("PC", 10),
    PAY_PLATFORM_20("WAP", 20),
    PAY_PLATFORM_31("APP_ANDROID", 31),
    PAY_PLATFORM_32("APP_IPHONE", 32);

    public static String

    getShowName(int index) {
        for (PayPlatform c : PayPlatform.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private PayPlatform(String showName, int index) {
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