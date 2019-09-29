package com.moba.emums.roleUser;

/**
 * 用户类型 确保和RoleType一一对应
 */
public enum UserType {
    USER_TYPE_1("系统超级管理员", 1), USER_TYPE_2("普通用户", 2);

    public static String getShowName(int index) {
        for (UserType c : UserType.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private UserType(String showName, int index) {
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
