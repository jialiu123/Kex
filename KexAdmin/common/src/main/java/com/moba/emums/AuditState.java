package com.moba.emums;

/**
 * @创建人: dingning
 * @创建时间: 2018-08-27 11:08
 */
public enum AuditState {
    AUDIT_STATE_X1("审核不通过", -1),

    AUDIT_STATE_0("未/待审核（默认）", 0),

    AUDIT_STATE_1("审核通过", 1),
    AUDIT_STATE_2("二次修改（待审核）", 2);

    public static String getName(int index) {
        for (AuditState c : AuditState.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    private AuditState(String name, int index) {
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
