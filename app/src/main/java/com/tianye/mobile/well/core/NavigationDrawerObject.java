package com.tianye.mobile.well.core;

/**
 * Created by lenovo on 2015/3/19.
 */
public class NavigationDrawerObject {
    public static final int TYPE_SEPERATOR = -1;
    public static final int TYPE_SUBHEADER = 0;
    public static final int TYPE_ITEM_MENU = 1;
    public static final int TYPE_ITEM_ORG = 2;


    private String title;
    private String iconString;

    private int type;
    private int imageResource;

    public NavigationDrawerObject(String title, String icon, int type) {
        this.title = title;
        this.iconString = icon;
        this.type = type;
    }

    public NavigationDrawerObject(String title, int type) {
        this.title = title;
        this.type = type;
    }

    public NavigationDrawerObject(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconString() {
        return iconString;
    }

    public void setIconString(String iconString) {
        this.iconString = iconString;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
