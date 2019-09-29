package com.moba.emums.orderInfo;

/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */
//是否退货

public enum IsTuiHuo {
    IS_TUI_HUO_1("退货退款", 1),

    IS_TUI_HUO_2("换货", 2),

    IS_TUI_HUO_3("仅退款", 3);

    public static String

    getShowName(int index) {
        for (IsTuiHuo c : IsTuiHuo.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private IsTuiHuo(String showName, int index) {
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