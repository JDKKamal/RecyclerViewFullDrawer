package com.jdkgroup.recyclerviewfulldrawer;

public class ModelDrawerMenu {
    private boolean selected;
    private String title;


    public ModelDrawerMenu() {

    }

    public ModelDrawerMenu(boolean selected, String title) {
        this.selected = selected;
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}