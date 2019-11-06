package com.mike.coordinatorlayoutexample.main;

import com.mike.coordinatorlayoutexample.Behavior.MainActivity;

class ItemDataBean {

    private String mItemName;
    private Class mClazz;
    private int mColor;


    public ItemDataBean(String itemName, Class clazz, int color) {
        this.mItemName = itemName;
        this.mClazz = clazz;
        this.mColor = color;
    }

    public String getItemName() {
        return mItemName;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public void setmItemName(String itemName) {
        this.mItemName = itemName;
    }

    public Class getClazz() {
        return mClazz;
    }


    public void setClazz(Class clazz) {
        this.mClazz = clazz;
    }
}
