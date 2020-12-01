package com.trinh.japanese.entities;

public class Lession {
    private String imgPath,name;

    public Lession(String imgPath, String name) {
        this.imgPath = imgPath;
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getName() {
        return name;
    }
}
