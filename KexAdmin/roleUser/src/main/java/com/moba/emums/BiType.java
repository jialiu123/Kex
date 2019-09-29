package com.moba.emums;

/**
 * @创建人: dingning
 * @创建时间: 2018-08-27 11:08
 */
public enum BiType {
    BI_TYPE_100("余额", 100),

    BI_TYPE_200("货款", 200);

    public static String getName(int index) {
        for (BiType c : BiType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private BiType(String name, int index) {
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
