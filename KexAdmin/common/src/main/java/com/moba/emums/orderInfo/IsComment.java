package com.moba.emums.orderInfo;

/**
 * @author li, bai, qiang
 * @version 2019-03-04 14:10
 * @date 2019-03-04 14:10
 */
//是否评价

public enum IsComment {
    IS_COMMENT_0("未评价", 0),

    IS_COMMENT_1("已评价", 1);

    public static String

    getShowName(int index) {
        for (IsComment c : IsComment.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private IsComment(String showName, int index) {
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