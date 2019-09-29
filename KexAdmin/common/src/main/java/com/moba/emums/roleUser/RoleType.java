package com.moba.emums.roleUser;

public enum RoleType {
    ROLE_TYPE_1("系统超级管理员", 1), ROLE_TYPE_2("注册会员", 2), ROLE_TYPE_3("商家", 3);

    public static String getName(int index) {
        for (RoleType c : RoleType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private RoleType(String name, int index) {
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
