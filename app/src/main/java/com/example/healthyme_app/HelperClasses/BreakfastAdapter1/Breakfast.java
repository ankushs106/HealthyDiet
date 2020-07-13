package com.example.healthyme_app.HelperClasses.BreakfastAdapter1;


public class Breakfast {

    int image;
    String title;
    String description;

    public Breakfast(int image, String title,String description) {
        this.image = image;
        this.title = title;
        this.description = description;

    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}