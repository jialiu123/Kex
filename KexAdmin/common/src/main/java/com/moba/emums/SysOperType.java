package com.moba.emums;

/** 操作日志类型 **/
public enum SysOperType {
    SysOperType_10("管理端登录日志", 10), SysOperType_20("插入操作日志", 20), SysOperType_30("修改操作日志", 30), SysOperType_40(
            "删除操作日志", 40);

    public static String getShowName(int index) {
        for (SysOperType c : SysOperType.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private SysOperType(String showName, int index) {
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