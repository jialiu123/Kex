package com.moba.emums;

/**
 * @创建人: liujia
 * @创建时间: 2018-08-27 11:08
 */
public enum EntpType {
    ENTP_TYPE_10("线上电商", 10),
    ENTP_TYPE_20("线下门店", 20),
    ENTP_TYPE_30("地推公司", 30);

    public static String getName(int index) {
        for (EntpType c : EntpType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private EntpType(String name, int index) {
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
