package com.moba.emums;

/**
 * @创建人: li, bai, qiang
 * @创建时间: 2018-08-27 11:08
 */
public enum MsgType {
    INFO_STATE_0("站内信息", 0),
    INFO_STATE_10("短信信息", 10);


    public static String

    getName(int index) {
        for (MsgType c : MsgType.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private MsgType(String showName, int index) {
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
