package com.moba.emums;


/**
 * 语言类型 *
 */
public enum BaseDataType {
    BaseDataType_10("系统管理员", 10), BaseDataType_20("用户类型", 20),
    BaseDataType_30("提现手续费", 30), BaseDataType_40("游戏终端", 40);


    public static String getName(int index) {
        for (BaseDataType c : BaseDataType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private BaseDataType(String name, int index) {
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