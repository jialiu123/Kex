package com.moba.emums;

/**
 * @创建人: dingning
 * @创建时间: 2018-08-27 11:08
 */
public enum BiChuOrRu {
    BI_CHU_OR_RU_1("进", 1), BI_CHU_OR_RU_X1("出", -1);

    public static String getName(int index) {
        for (BiChuOrRu c : BiChuOrRu.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private BiChuOrRu(String name, int index) {
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
