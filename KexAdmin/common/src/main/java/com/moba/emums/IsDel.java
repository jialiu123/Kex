package com.moba.emums;

/**
 * @创建人: liujia
 * @创建时间: 2018-08-27 11:08
 */
public enum IsDel {
    IS_DEL_0("未删除", 0), IS_DEL_1("已删除", 1);

    public static String getName(int index) {
        for (IsDel c : IsDel.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private IsDel(String name, int index) {
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
