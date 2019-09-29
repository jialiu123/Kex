package com.moba.emums;


/**
 * 返回的状态位 *
 */
public enum CodeType {
    CODE_TYPE_200("成功", 200), CODE_TYPE_100("接口错误", 100), CODE_TYPE_101("参数错误", 101), CODE_TYPE_102("数据不存在", 102),
    CODE_TYPE_103("特殊处理", 103),CODE_TYPE_104("消息提示", 104);

    public static String getName(int index) {
        for (CodeType c : CodeType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private CodeType(String name, int index) {
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