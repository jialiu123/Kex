package com.moba.emums;

public enum YesOrNo {
    YES_OR_NO_0("否", 0), YES_OR_NO_1("是", 1);

    public static String getShowName(int index) {
        for (YesOrNo c : YesOrNo.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private YesOrNo(String showName, int index) {
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