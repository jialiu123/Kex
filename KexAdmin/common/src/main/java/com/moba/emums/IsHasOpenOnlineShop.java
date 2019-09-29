package com.moba.emums;

/**
 * @创建人: liujia
 * @创建时间: 2018-08-27 11:08
 */
public enum IsHasOpenOnlineShop {
    IS_HAS_OPEN_ONLINE_SHOP_0("开通", 0), IS_HAS_OPEN_ONLINE_SHOP_1("未开通", 1);

    public static String getName(int index) {
        for (IsHasOpenOnlineShop c : IsHasOpenOnlineShop.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private IsHasOpenOnlineShop(String name, int index) {
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
