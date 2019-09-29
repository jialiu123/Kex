package com.moba.emums.userMoneyApply;

/**
 * @创建人: li, bai, qiang
 * @创建时间: 2019-3-6 11:57
 */
public enum CashType {
    AUDIT_STATE_10("余额提现", 10);

    public static String getName(int index) {
        for (CashType c : CashType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private CashType(String name, int index) {
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
