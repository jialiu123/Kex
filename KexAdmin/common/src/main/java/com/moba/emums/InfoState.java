package com.moba.emums;

/**
 * @创建人: li, bai, qiang
 * @创建时间: 2018-08-27 11:08
 */
public enum InfoState {
    INFO_STATE_0("草稿未发送", 0),
    INFO_STATE_1("已发送未读", 1),
    INFO_STATE_2("已发送已读", 2),
    INFO_STATE_3("回收站", 3);

    public static String getShowName(int index) {
        for (InfoState c : InfoState.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private InfoState(String showName, int index) {
        this.showName = showName;
        this.index = index;
    }

    private String showName;

    private int index;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String name) {
        this.showName = showName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
