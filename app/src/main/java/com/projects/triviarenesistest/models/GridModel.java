package com.projects.triviarenesistest.models;

import android.graphics.Bitmap;

public class GridModel {
    private int image;
    private String title;
    private int category;

    public GridModel(int image, String title, int category) {
        this.image = image;
        this.title = title;
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
