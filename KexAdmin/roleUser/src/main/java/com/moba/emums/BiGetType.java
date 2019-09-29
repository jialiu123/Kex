package com.moba.emums;

/**
 * @创建人: dingning
 * @创建时间: 2018-08-27 11:08
 */
public enum BiGetType {
    BI_GET_TYPE_1("卖出商品获得余额", 1), BI_GET_TYPE_10("余额提现", 10);

    public static String getName(int index) {
        for (BiGetType c : BiGetType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private BiGetType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    private String name;

    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
