package com.moba.emums;

/**
 * @创建人: dingning
 * @创建时间: 2018-08-27 11:08
 */
public enum ClsScope {
    CLS_SCORE_0("游戏", 0), CLS_SCORE_10("游戏商品", 10);

    public static String getName(int index) {
        for (ClsScope c : ClsScope.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private ClsScope(String name, int index) {
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
