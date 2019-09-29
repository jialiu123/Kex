package com.moba.emums;


/**
 * 语言类型 *
 */
public enum LanguageType {
    China("中文", "zh-CN"), English("English", "en-US");

    public static String getShowName(String index) {
        for (LanguageType c : LanguageType.values()) {
            if (c.getIndex() == index) {
                return c.showName;
            }
        }
        return null;
    }

    private LanguageType(String showName, String index) {
        this.showName = showName;
        this.index = index;
    }

    private String showName;

    private String index;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}