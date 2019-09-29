package com.moba.emums;

/**
 * @创建人: liujia
 * @创建时间: 2018-08-27 11:08
 */
public enum BaseImgType {
    BaseImgType_10("商品轮播图", 10);

    public static String getName(int index) {
        for (BaseImgType c : BaseImgType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private BaseImgType(String name, int index) {
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
